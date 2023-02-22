package com.personal.beans.service.contracts;

import com.personal.beans.models.Version;

import java.util.List;

public interface VersionService {

    int totalDownloadCount();

    List<Version> findByBean(String bean);

    List<Version> filterVersionsForApproval(String bean);

}
