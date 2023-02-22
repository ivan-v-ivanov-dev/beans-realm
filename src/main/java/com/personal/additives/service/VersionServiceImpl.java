package com.personal.additives.service;

import com.personal.additives.models.Version;
import com.personal.additives.repository.postgres.VersionRepository;
import com.personal.additives.service.contracts.VersionService;
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
    public List<Version> findByAdditive(String additive) {
        return this.versionRepository.filter(additive);
    }

    @Override
    public List<Version> filterVersionsForApproval(String additive) {
        return this.versionRepository.filterVersionsForApproval(additive);
    }
}
