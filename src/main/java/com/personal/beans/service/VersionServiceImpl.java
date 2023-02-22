package com.personal.beans.service;

import com.personal.beans.models.Version;
import com.personal.beans.repository.postgres.VersionRepository;
import com.personal.beans.service.contracts.VersionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionServiceImpl implements VersionService {

    private final VersionRepository versionRepository;

    public VersionServiceImpl(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    @Override
    public int totalDownloadCount() {
        return this.versionRepository.totalDownloadCount();
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
