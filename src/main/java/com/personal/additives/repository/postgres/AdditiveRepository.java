package com.personal.additives.repository.postgres;

import com.personal.additives.models.Additive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.personal.additives.constants.Constants.*;
import static com.personal.additives.repository.Queries.*;

public interface AdditiveRepository extends JpaRepository<Additive, Integer> {

    @Query(value = FIND_LAST_SIX_UPLOADED_ADDITIVES, nativeQuery = true)
    List<Additive> latest();

    @Query(value = FIND_FIRST_SIX_MOST_DOWNLOADED_ADDITIVES, nativeQuery = true)
    List<Additive> mostDownloaded();

    @Query(value = FIND_FIRST_SIX_TOP_RATED_ADDITIVES, nativeQuery = true)
    List<Additive> topRated();

    @Query(value = FILTER_ADDITIVE, nativeQuery = true)
    List<Additive> filter(@Param(ADDITIVE) String additive, @Param(CREATOR) String creator,
                          @Param(TAG) String tag, @Param(TYPE) String type,
                          @Param(DEVICE) String device, @Param(OFFSET) int offset);

    @Query(value = FIND_ADDITIVE_BY_STATUS, nativeQuery = true)
    List<Additive> findByStatus(@Param(STATUS) String status, @Param(OFFSET) int offset);

}
