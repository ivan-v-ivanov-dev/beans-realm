package com.personal.beans.service;

import com.personal.beans.models.Status;
import com.personal.beans.models.Version;
import com.personal.beans.repository.postgres.StatusRepository;
import com.personal.beans.repository.postgres.VersionRepository;
import com.personal.beans.service.contracts.RedisCacheService;
import com.personal.beans.service.contracts.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.service.constants.LoggerConstants.*;
import static com.personal.beans.service.constants.RedisKeysConstants.*;
import static com.personal.beans.service.constants.ServiceConstants.*;

@Service
@Slf4j
public class VersionServiceImpl implements VersionService {

    private final VersionRepository versionRepository;
    private final RedisCacheService redisCacheService;
    private final StatusRepository statusRepository;

    public VersionServiceImpl(VersionRepository versionRepository,
                              RedisCacheService redisCacheService,
                              StatusRepository statusRepository) {
        this.versionRepository = versionRepository;
        this.redisCacheService = redisCacheService;
        this.statusRepository = statusRepository;
    }

    @Override
    public int totalDownloadCount() {
        if (this.redisCacheService.containsKey(TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS)) {
            log.info(FROM_REDIS_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS);
            return this.redisCacheService.retrieve(TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS, Integer.class).get(0);
        }

        int totalDownloadCount = this.versionRepository.totalDownloadCount();
        this.redisCacheService.save(TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS, totalDownloadCount);
        log.info(FROM_POSTGRES_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS);
        return totalDownloadCount;
    }

    @Override
    public int beanDownloadCount(String bean) {
        return versionRepository.beanDownloadCount(bean);
    }

    @Override
    public List<Version> findByBean(String bean) {
        String hashKey = BEAN_ + bean.replace(EMPTY_SPACE, UNDERSCORE).toUpperCase() + _VERSIONS;

        if (this.redisCacheService.containsKey(hashKey)) {
            log.info(String.format(FROM_REDIS_VERSIONS_FOR_BEAN_TEMPLATE, bean));
            return this.redisCacheService.retrieve(hashKey, Version.class);
        }

        List<Version> versions = this.versionRepository.filter(bean);
        this.redisCacheService.save(hashKey, versions);
        log.info(String.format(FROM_POSTGRES_VERSIONS_FOR_BEAN_TEMPLATE, bean));
        return versions;
    }

    @Override
    public List<Version> notApprovedByBean(String bean) {
        return this.versionRepository.notApprovedByBean(bean);
    }

    @Override
    public int countByBean(String beanName) {
        String hashKey = VERSIONS_COUNT_FOR_BEAN_ + beanName.replace(EMPTY_SPACE, UNDERSCORE).toUpperCase();

        if (this.redisCacheService.containsKey(hashKey)) {
            log.info(String.format(FROM_REDIS_VERSIONS_COUNT_FOR_BEAN_TEMPLATE, beanName));
            return this.redisCacheService.retrieve(hashKey, Integer.class).get(0);
        }

        int versionsCount = this.versionRepository.countByBeanName(beanName);
        this.redisCacheService.save(hashKey, versionsCount);
        log.info(String.format(FROM_POSTGRES_VERSIONS_COUNT_FOR_BEAN_TEMPLATE, beanName));
        return versionsCount;
    }

    @Override
    public void approveByBean(String versionName, String beanName) {
        Version version = this.versionRepository.findByNameAndBeanName(versionName, beanName);
        Status approvedStatus = this.statusRepository.findByName(APPROVED);
        version.setStatus(approvedStatus);
        this.versionRepository.save(version);
        log.info(String.format(APPROVE_VERSION_FOR_BEAN_TEMPLATE, version.getName(), beanName));
    }
}
