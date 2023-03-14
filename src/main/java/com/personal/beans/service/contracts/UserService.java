package com.personal.beans.service.contracts;

import com.personal.beans.models.User;

import java.util.List;

public interface UserService {

    int userCount();

    List<User> findAll();
}
