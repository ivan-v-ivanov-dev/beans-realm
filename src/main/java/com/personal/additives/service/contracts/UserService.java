package com.personal.additives.service.contracts;

import com.personal.additives.models.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers(boolean enabled, String username);

    User create(User user);

    User update(User user);

    void delete(User user);

}
