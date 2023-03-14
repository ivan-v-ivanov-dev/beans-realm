package com.personal.beans.repository.postgres;

import com.personal.beans.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static com.personal.beans.repository.Queries.FIND_ALL_USERS_ORDER_BY_USERNAME_ASC;
import static com.personal.beans.repository.Queries.FIND_ENABLED_USER_COUNT;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = FIND_ENABLED_USER_COUNT, nativeQuery = true)
    int userCount();

    @Query(FIND_ALL_USERS_ORDER_BY_USERNAME_ASC)
    List<User> findAllUsers();

    User findByUsername(String username);
}
