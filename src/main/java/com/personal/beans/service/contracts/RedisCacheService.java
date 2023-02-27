package com.personal.beans.service.contracts;

import com.personal.beans.models.Bean;

import java.util.List;

public interface RedisCacheService {

    boolean containsKey(String key);

    <T> List<T> retrieve(String key, Class<T> tClazz);

    void saveBeans(String key, List<Bean> beans);

    void saveEntity(String key, int entity);
}
