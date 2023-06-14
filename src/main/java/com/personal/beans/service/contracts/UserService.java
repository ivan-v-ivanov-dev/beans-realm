package com.personal.beans.service.contracts;

import com.personal.beans.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    int userCount();

    List<User> findAll();

    void modifyStatus(String username, String action);
}
