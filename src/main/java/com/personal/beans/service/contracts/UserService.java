package com.personal.beans.service.contracts;

import com.personal.beans.models.User;

import java.util.List;

public interface UserService {

    int userCount();

    List<User> findAllUsers(boolean enabled, String username);

    User create(User user);

    User update(User user);

    void delete(User user);

}
