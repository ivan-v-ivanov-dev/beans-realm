package com.personal.beans.repository.postgres;

import com.personal.beans.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.personal.beans.repository.Queries.*;

public interface VersionRepository extends JpaRepository<Version, Integer> {

    @Query(value = FIND_TOTAL_APPROVED_BEAN_DOWNLOADS, nativeQuery = true)
    int totalDownloadCount();

    @Query(value = BEAN_DOWNLOAD_COUNT, nativeQuery = true)
    int beanDownloadCount(@Param("bean") String bean);

    @Query(FILTER_BEANS_APPROVED_VERSIONS)
    List<Version> filter(@Param("bean") String bean);

    @Query(FILTER_UNAPPROVED_VERSION_FOR_BEANS)
    List<Version> notApprovedByBean(@Param("bean") String bean);

    @Query(value = COUNT_APPROVED_VERSIONS_BY_BEAN_NAME, nativeQuery = true)
    int countByBeanName(String beanName);

    Version findByNameAndBeanName(String name, String beanName);
}
