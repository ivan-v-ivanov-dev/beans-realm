package com.personal.beans.service.contracts;

import com.personal.beans.models.Bean;
import com.personal.beans.models.Version;

import java.util.List;

public interface RedisCacheService {

    boolean containsKey(String key);

    <T> List<T> retrieve(String key, Class<T> tClazz);

    void saveSingleBean(String key, Bean bean);

    void saveBeans(String key, List<Bean> beans);

    void saveVersions(String key, List<Version> versions);

    void saveEntity(String key, int entity);

}
