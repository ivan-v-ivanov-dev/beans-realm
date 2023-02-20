package com.personal.additives.repository.postgres;

import com.personal.additives.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.personal.additives.repository.Queries.FILTER_USERS;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(FILTER_USERS)
    List<User> findAllUsersOrderedByUsernameAsc(boolean enabled, @Param("username") String username);
}
