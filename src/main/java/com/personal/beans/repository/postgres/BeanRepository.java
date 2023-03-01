package com.personal.beans.repository.postgres;

import com.personal.beans.models.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.personal.beans.constants.Constants.*;
import static com.personal.beans.repository.Queries.*;

public interface BeanRepository extends JpaRepository<Bean, Integer> {

    @Query(value = FIND_LAST_SIX_UPLOADED_BEANS, nativeQuery = true)
    List<Bean> latest();

    @Query(value = FIND_FIRST_SIX_MOST_DOWNLOADED_BEANS, nativeQuery = true)
    List<Bean> mostDownloaded();

    @Query(value = FIND_FIRST_SIX_TOP_RATED_BEANS, nativeQuery = true)
    List<Bean> topRated();

    @Query(value = FIND_APPROVED_BEANS_COUNT, nativeQuery = true)
    int beansCount();

    @Query(FIND_BEAN_BY_NAME)
    Bean findByName(String beanName);

    @Query(value = FILTER_BEANS, nativeQuery = true)
    List<Bean> filter(@Param(TAG) Integer tag, @Param(TYPE) Integer type,
                      @Param(DEVICE) Integer device, @Param(OFFSET) Integer offset);

    @Query(value = FIND_BEANS_BY_STATUS, nativeQuery = true)
    List<Bean> findByStatus(@Param(STATUS) String status, @Param(OFFSET) int offset);

}
