package com.personal.beans.repository.postgres;

import com.personal.beans.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.personal.beans.repository.Queries.FILTER_USERS;
import static com.personal.beans.repository.Queries.FIND_ENABLED_USER_COUNT;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = FIND_ENABLED_USER_COUNT, nativeQuery = true)
    int userCount();

    @Query(FILTER_USERS)
    List<User> findAllUsersOrderedByUsernameAsc(boolean enabled, @Param("username") String username);

    User findByUsername(String username);
}
