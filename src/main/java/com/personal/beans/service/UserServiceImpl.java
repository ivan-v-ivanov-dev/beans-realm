package com.personal.beans.service;

import com.personal.beans.models.User;
import com.personal.beans.repository.postgres.UserRepository;
import com.personal.beans.service.contracts.RedisCacheService;
import com.personal.beans.service.contracts.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.service.constants.LoggerConstants.FROM_POSTGRES_TOTAL_REGISTERED_USERS;
import static com.personal.beans.service.constants.LoggerConstants.FROM_REDIS_TOTAL_REGISTERED_USERS;
import static com.personal.beans.service.constants.RedisKeysConstants.TOTAL_REGISTERED_USERS;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisCacheService redisCacheService;

    public UserServiceImpl(UserRepository userRepository,
                           RedisCacheService redisCacheService) {
        this.userRepository = userRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public int userCount() {
        if (this.redisCacheService.containsKey(TOTAL_REGISTERED_USERS)) {
            log.info(FROM_REDIS_TOTAL_REGISTERED_USERS);
            return this.redisCacheService.retrieve(TOTAL_REGISTERED_USERS, Integer.class).get(0);
        }

        int totalRegisteredUsers = this.userRepository.userCount();
        this.redisCacheService.save(TOTAL_REGISTERED_USERS, totalRegisteredUsers);
        log.info(FROM_POSTGRES_TOTAL_REGISTERED_USERS);
        return totalRegisteredUsers;
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
