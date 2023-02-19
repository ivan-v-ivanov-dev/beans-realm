package com.personal.additives.repository.postgres;

import com.personal.additives.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.admin = false AND u.enabled = :enabled AND " +
            "(:username is null OR username = :username) ORDER BY u.username ASC")
    List<User> findAllUsersOrderedByUsernameAsc(boolean enabled, @Param("username") String username);
}
