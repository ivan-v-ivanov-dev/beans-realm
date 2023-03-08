package com.personal.beans.service;

import com.personal.beans.models.Bean;
import com.personal.beans.models.Version;
import com.personal.beans.models.dto.BeanDto;
import com.personal.beans.repository.postgres.BeanRepository;
import com.personal.beans.repository.postgres.VersionRepository;
import com.personal.beans.service.contracts.BeanService;
import com.personal.beans.service.contracts.EntityMapperService;
import com.personal.beans.service.contracts.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.personal.beans.constants.Constants.*;

@Service
@Slf4j
public class BeanServiceImpl implements BeanService {

    private final BeanRepository beanRepository;
    private final VersionRepository versionRepository;
    private final RedisCacheService redisCacheService;
    private final EntityMapperService entityMapperService;

    public BeanServiceImpl(BeanRepository beanRepository,
                           VersionRepository versionRepository,
                           RedisCacheService redisCacheService,
                           EntityMapperService entityMapperService) {
        this.beanRepository = beanRepository;
        this.versionRepository = versionRepository;
        this.redisCacheService = redisCacheService;
        this.entityMapperService = entityMapperService;
    }

    @Override
    public List<Bean> latest() {
        if (this.redisCacheService.containsKey(LATEST_BEANS)) {
            log.info(FROM_REDIS_LATEST_BEANS);
            return this.redisCacheService.retrieve(LATEST_BEANS, Bean.class);
        }

        List<Bean> beans = this.beanRepository.latest();
        calculateAllBeansDownloadCount(beans);
        this.redisCacheService.save(LATEST_BEANS, beans);
        log.info(FROM_POSTGRES_LATEST_BEANS);
        return beans;
    }

    @Override
    public List<Bean> mostDownloaded() {
        if (this.redisCacheService.containsKey(MOST_DOWNLOADED_BEANS)) {
            log.info(FROM_REDIS_MOST_DOWNLOADED_BEANS);
            return this.redisCacheService.retrieve(MOST_DOWNLOADED_BEANS, Bean.class);
        }

        List<Bean> beans = this.beanRepository.mostDownloaded();
        calculateAllBeansDownloadCount(beans);
        this.redisCacheService.save(MOST_DOWNLOADED_BEANS, beans);
        log.info(FROM_POSTGRES_MOST_DOWNLOADED_BEANS);
        return beans;
    }

    @Override
    public List<Bean> topRated() {
        if (this.redisCacheService.containsKey(TOP_RATED_BEANS)) {
            log.info(FROM_REDIS_TOP_RATED_BEANS);
            return this.redisCacheService.retrieve(TOP_RATED_BEANS, Bean.class);
        }

        List<Bean> beans = this.beanRepository.topRated();
        calculateAllBeansDownloadCount(beans);
        this.redisCacheService.save(TOP_RATED_BEANS, beans);
        log.info(FROM_POSTGRES_TOP_RATED_BEANS);
        return beans;
    }

    @Override
    public int beansCount() {
        if (this.redisCacheService.containsKey(TOTAL_APPROVED_BEANS_COUNT)) {
            log.info(FROM_REDIS_TOTAL_APPROVED_BEANS_COUNT);
            return this.redisCacheService.retrieve(TOTAL_APPROVED_BEANS_COUNT, Integer.class).get(0);
        }

        int totalApprovedBeansCount = this.beanRepository.beansCount();
        this.redisCacheService.save(TOTAL_APPROVED_BEANS_COUNT, totalApprovedBeansCount);
        log.info(FROM_POSTGRES_TOTAL_APPROVED_BEANS_COUNT);
        return totalApprovedBeansCount;
    }

    @Override
    public Bean findByName(String beanName) {
        String hashKey = beanName.replace(EMPTY_SPACE, UNDERSCORE).toUpperCase();

        if (this.redisCacheService.containsKey(hashKey)) {
            log.info(String.format(FROM_REDIS_SINGLE_BEAN_TEMPLATE, beanName));
            return this.redisCacheService.retrieve(hashKey, Bean.class).get(0);
        }

        Bean bean = this.beanRepository.findByName(beanName);
        calculateSingleBeanTotalDownloadCount(bean);
        this.redisCacheService.save(hashKey, bean);
        log.info(String.format(FROM_POSTGRES_SINGLE_BEAN_TEMPLATE, beanName));
        return bean;
    }

    @Override
    public void create(BeanDto beanDto) {
        try {
            Bean bean = this.entityMapperService.toBean(beanDto);
            bean = this.beanRepository.save(bean);
            Version version = this.entityMapperService.toFirstVersion(bean, beanDto);
            this.versionRepository.save(version);
        } catch (IOException | NoSuchAlgorithmException ioException) {
            log.error(ioException.getMessage());
        }
    }

    @Override
    public List<Bean> filter(Integer tag, Integer type, Integer device, Integer offset) {
        return this.beanRepository.filter(tag, type, device, offset);
    }

    @Override
    public List<Bean> notApproved() {
        List<Bean> beans = this.beanRepository.notApproved();
        calculateAllBeansDownloadCount(beans);
        return beans;
    }

    private void calculateAllBeansDownloadCount(List<Bean> beans) {
        beans.forEach(this::calculateSingleBeanTotalDownloadCount);
    }

    private void calculateSingleBeanTotalDownloadCount(Bean bean) {
        bean.setTotalDownloads(this.versionRepository.beanDownloadCount(bean.getName()));
    }
}
