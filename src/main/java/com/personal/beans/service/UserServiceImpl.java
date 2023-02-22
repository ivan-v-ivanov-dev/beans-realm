package com.personal.beans.service;

import com.personal.beans.models.User;
import com.personal.beans.repository.postgres.UserRepository;
import com.personal.beans.service.contracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int userCount() {
        return this.userRepository.userCount();
    }

    @Override
    public List<User> findAllUsers(boolean enabled, String username) {
        return this.userRepository.findAllUsersOrderedByUsernameAsc(enabled, username);
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }
}
