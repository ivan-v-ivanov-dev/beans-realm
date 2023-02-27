package com.personal.beans.service;

import com.personal.beans.models.Bean;
import com.personal.beans.service.contracts.RedisCacheService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean containsKey(String key) {
        return Boolean.TRUE.equals(this.redisTemplate.opsForHash().getOperations().hasKey(key));
    }

    @Override
    public <T> List<T> retrieve(String key, Class<T> tClazz) {
        return this.redisTemplate
                .opsForHash()
                .entries(key)
                .values()
                .stream()
                .map(tClazz::cast)
                .collect(Collectors.toList());
    }

    @Override
    public void saveBeans(String key, List<Bean> beans) {
        beans.forEach(currentBean -> this.redisTemplate.opsForHash().put(key, currentBean.getName(), currentBean));
    }

    @Override
    public void saveEntity(String key, int entity) {
        this.redisTemplate.opsForHash().put(key, String.valueOf(entity), entity);
    }
}
