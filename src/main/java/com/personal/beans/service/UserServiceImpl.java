package com.personal.beans.service;

import com.personal.beans.models.User;
import com.personal.beans.repository.postgres.BeanRepository;
import com.personal.beans.repository.postgres.UserRepository;
import com.personal.beans.service.contracts.RedisCacheService;
import com.personal.beans.service.contracts.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.service.constants.LoggerConstants.*;
import static com.personal.beans.service.constants.RedisKeysConstants.TOTAL_REGISTERED_USERS;
import static com.personal.beans.service.constants.ServiceConstants.ENABLE;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BeanRepository beanRepository;
    private final RedisCacheService redisCacheService;

    public UserServiceImpl(UserRepository userRepository,
                           BeanRepository beanRepository,
                           RedisCacheService redisCacheService) {
        this.userRepository = userRepository;
        this.beanRepository = beanRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public int userCount() {
        if (this.redisCacheService.containsKey(TOTAL_REGISTERED_USERS)) {
            log.info(FROM_REDIS_ALL_REGISTERED_USERS_COUNT);
            return this.redisCacheService.retrieve(TOTAL_REGISTERED_USERS, Integer.class).get(0);
        }

        int totalRegisteredUsers = this.userRepository.userCount();
        this.redisCacheService.save(TOTAL_REGISTERED_USERS, totalRegisteredUsers);
        log.info(FROM_POSTGRES_ALL_REGISTERED_USERS_COUNT);
        return totalRegisteredUsers;
    }

    @Override
    public List<User> findAll() {
        List<User> users = this.userRepository.findAllUsers();
        findUploadedBeansForEachUser(users);
        log.info(FROM_POSTGRES_ALL_USERS);
        return users;
    }

    @Override
    public void modifyStatus(String username, String action) {
        User user = this.userRepository.findByUsername(username);
        user.setEnabled(action.equals(ENABLE));
        this.userRepository.save(user);
        log.info(String.format(CHANGE_STATUS_OF_USER_TEMPLATE, username, action));
    }

    private void findUploadedBeansForEachUser(List<User> users) {
        users.forEach(user -> user.setUploadedBeans(this.beanRepository.findBeansCountByUsername(user.getUsername())));
    }
}
