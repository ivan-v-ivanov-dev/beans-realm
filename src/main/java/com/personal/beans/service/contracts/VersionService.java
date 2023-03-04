package com.personal.beans.service.contracts;

import com.personal.beans.models.Version;

import java.util.List;

public interface VersionService {

    int totalDownloadCount();

    int beanDownloadCount(String bean);

    List<Version> findByBean(String bean);

    List<Version> filterVersionsForApproval(String bean);

    int countByBean(String beanName);
}
