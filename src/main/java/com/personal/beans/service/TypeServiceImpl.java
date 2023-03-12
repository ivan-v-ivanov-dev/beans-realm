package com.personal.beans.service;

import com.personal.beans.models.Type;
import com.personal.beans.repository.postgres.TypeRepository;
import com.personal.beans.service.contracts.RedisCacheService;
import com.personal.beans.service.contracts.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.service.constants.LoggerConstants.FROM_POSTGRES_ALL_TYPES;
import static com.personal.beans.service.constants.LoggerConstants.FROM_REDIS_ALL_TYPES;
import static com.personal.beans.service.constants.RedisKeysConstants.ALL_TYPES;
import static com.personal.beans.service.constants.ServiceConstants.NAME;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final RedisCacheService redisCacheService;

    public TypeServiceImpl(TypeRepository typeRepository,
                           RedisCacheService redisCacheService) {
        this.typeRepository = typeRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public List<Type> findAll() {
        if (this.redisCacheService.containsKey(ALL_TYPES)) {
            log.info(FROM_REDIS_ALL_TYPES);
            return this.redisCacheService.retrieve(ALL_TYPES, Type.class);
        }

        List<Type> types = this.typeRepository.findAll(Sort.by(NAME).ascending());
        this.redisCacheService.save(ALL_TYPES, types);
        log.info(FROM_POSTGRES_ALL_TYPES);
        return types;
    }
}
