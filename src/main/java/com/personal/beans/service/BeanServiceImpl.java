package com.personal.beans.service;

import com.personal.beans.models.Bean;
import com.personal.beans.repository.postgres.BeanRepository;
import com.personal.beans.repository.postgres.VersionRepository;
import com.personal.beans.service.contracts.BeanService;
import com.personal.beans.service.contracts.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BeanServiceImpl implements BeanService {

    private final BeanRepository beanRepository;
    private final VersionRepository versionRepository;
    private final RedisCacheService redisCacheService;

    public BeanServiceImpl(BeanRepository beanRepository,
                           VersionRepository versionRepository,
                           RedisCacheService redisCacheService) {
        this.beanRepository = beanRepository;
        this.versionRepository = versionRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public List<Bean> latest() {
        if (redisCacheService.containsKey("LATEST_BEANS")) {
            log.info("Retrieve Latest Beans from Redis Cache");
            return this.redisCacheService.retrieve("LATEST_BEANS", Bean.class);
        } else {
            List<Bean> beans = this.beanRepository.latest();
            calculateBeansTotalDownloadCount(beans);
            this.redisCacheService.saveBeans("LATEST_BEANS", beans);
            log.info("Retrieve Latest Beans from Postgres DB");
            return beans;
        }
    }

    @Override
    public List<Bean> mostDownloaded() {
        if (redisCacheService.containsKey("MOST_DOWNLOADED_BEANS")) {
            log.info("Retrieve Most Downloaded Beans from Redis Cache");
            return this.redisCacheService.retrieve("MOST_DOWNLOADED_BEANS", Bean.class);
        } else {
            List<Bean> beans = this.beanRepository.mostDownloaded();
            calculateBeansTotalDownloadCount(beans);
            this.redisCacheService.saveBeans("MOST_DOWNLOADED_BEANS", beans);
            log.info("Retrieve Most Downloaded Beans from Postgres DB");
            return beans;
        }
    }

    @Override
    public List<Bean> topRated() {
        if (redisCacheService.containsKey("TOP_RATED_BEANS")) {
            log.info("Retrieve Top Rated Beans from Redis Cache");
            return this.redisCacheService.retrieve("TOP_RATED_BEANS", Bean.class);
        } else {
            List<Bean> beans = this.beanRepository.topRated();
            calculateBeansTotalDownloadCount(beans);
            this.redisCacheService.saveBeans("TOP_RATED_BEANS", beans);
            log.info("Retrieve Top Rated Beans from Postgres DB");
            return beans;
        }
    }

    @Override
    public int beansCount() {
        return this.beanRepository.beansCount();
    }

    @Override
    public Bean create(Bean bean) {
        return this.beanRepository.save(bean);
    }

    @Override
    public List<Bean> filter(String bean, String creator, String tag, String type,
                             String device, int offset) {
        return this.beanRepository.filter(bean, creator, tag, type, device, offset);
    }

    @Override
    public List<Bean> findByStatus(String status, int offset) {
        return this.beanRepository.findByStatus(status, offset);
    }

    private void calculateBeansTotalDownloadCount(List<Bean> beans) {
        beans.forEach(currentBean ->
                currentBean.setTotalDownloads(
                        this.versionRepository.beanDownloadCount(currentBean.getName())));
    }
}
