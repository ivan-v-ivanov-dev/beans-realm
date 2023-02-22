package com.personal.additives.service.contracts;

import com.personal.additives.models.Version;

import java.util.List;

public interface VersionService {

    int totalDownloadCount();

    List<Version> findByAdditive(String additive);

    List<Version> filterVersionsForApproval(String additive);

}
