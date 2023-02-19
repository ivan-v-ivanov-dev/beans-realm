package com.personal.additives.repository.postgres;

import com.personal.additives.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.admin = false AND u.enabled = :enabled ORDER BY u.username ASC")
    List<User> findAllUsersOrderedByUsernameAsc(boolean enabled);
}
