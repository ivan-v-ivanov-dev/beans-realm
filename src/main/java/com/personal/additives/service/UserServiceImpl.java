package com.personal.additives.service;

import com.personal.additives.models.User;
import com.personal.additives.repository.postgres.UserRepository;
import com.personal.additives.service.contracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
