package com.personal.beans.service;

import com.personal.beans.models.Version;
import com.personal.beans.repository.postgres.VersionRepository;
import com.personal.beans.service.contracts.RedisCacheService;
import com.personal.beans.service.contracts.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.constants.Constants.*;

@Service
@Slf4j
public class VersionServiceImpl implements VersionService {

    private final VersionRepository versionRepository;
    private final RedisCacheService redisCacheService;

    public VersionServiceImpl(VersionRepository versionRepository,
                              RedisCacheService redisCacheService) {
        this.versionRepository = versionRepository;
        this.redisCacheService = redisCacheService;
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
    public List<Version> filterVersionsForApproval(String bean) {
        return this.versionRepository.filterVersionsForApproval(bean);
    }
}
