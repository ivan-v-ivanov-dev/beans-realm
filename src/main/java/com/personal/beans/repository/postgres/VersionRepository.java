package com.personal.beans.repository.postgres;

import com.personal.beans.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.personal.beans.constants.Constants.BEAN;
import static com.personal.beans.repository.Queries.*;

public interface VersionRepository extends JpaRepository<Version, Integer> {

    @Query(value = FIND_TOTAL_APPROVED_BEAN_DOWNLOADS, nativeQuery = true)
    int totalDownloadCount();

    @Query(value = BEAN_DOWNLOAD_COUNT, nativeQuery = true)
    int beanDownloadCount(@Param(BEAN) String bean);

    @Query(FILTER_BEANS_APPROVED_VERSIONS)
    List<Version> filter(@Param(BEAN) String bean);

    @Query(FILTER_BEANS_TO_APPROVE)
    List<Version> filterVersionsForApproval(@Param(BEAN) String bean);

    @Query(value = COUNT_APPROVED_VERSIONS_BY_BEAN_NAME, nativeQuery = true)
    int countByBeanName(String beanName);
}
