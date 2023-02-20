package com.personal.additives.repository.postgres;

import com.personal.additives.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.personal.additives.constants.Constants.ADDITIVE;
import static com.personal.additives.repository.Queries.FILTER_ADDITIVE_APPROVED_VERSIONS;
import static com.personal.additives.repository.Queries.FILTER_ADDITIVE_TO_APPROVE;

public interface VersionRepository extends JpaRepository<Version, Integer> {

    @Query(FILTER_ADDITIVE_APPROVED_VERSIONS)
    List<Version> filter(@Param(ADDITIVE) String additive);

    @Query(FILTER_ADDITIVE_TO_APPROVE)
    List<Version> filterVersionsForApproval(@Param("additive") String additive);
}
