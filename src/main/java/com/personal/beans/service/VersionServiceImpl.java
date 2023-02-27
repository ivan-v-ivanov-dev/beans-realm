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
            log.info(RETRIEVE_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS_FROM_REDIS_CACHE);
            return this.redisCacheService.retrieve(TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS, Integer.class).get(0);
        }

        int totalDownloadCount = this.versionRepository.totalDownloadCount();
        this.redisCacheService.saveEntity(TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS, totalDownloadCount);
        log.info(RETRIEVE_TOTAL_DOWNLOAD_COUNT_FOR_ALL_APPROVED_BEANS_FROM_POSTGRES_DB);
        return totalDownloadCount;
    }

    @Override
    public int beanDownloadCount(String bean) {
        return versionRepository.beanDownloadCount(bean);
    }

    @Override
    public List<Version> findByBean(String bean) {
        return this.versionRepository.filter(bean);
    }

    @Override
    public List<Version> filterVersionsForApproval(String bean) {
        return this.versionRepository.filterVersionsForApproval(bean);
    }
}
