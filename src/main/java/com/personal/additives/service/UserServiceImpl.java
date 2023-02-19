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
    public List<User> findAllUsersOrderedByUsernameAsc(boolean enabled) {
        return this.userRepository.findAllUsersOrderedByUsernameAsc(enabled);
    }
}
