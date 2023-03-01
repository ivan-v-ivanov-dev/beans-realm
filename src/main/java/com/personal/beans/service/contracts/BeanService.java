package com.personal.beans.service.contracts;

import com.personal.beans.models.Bean;

import java.util.List;

public interface BeanService {

    List<Bean> latest();

    List<Bean> mostDownloaded();

    List<Bean> topRated();

    int beansCount();

    Bean findByName(String beanName);

    Bean create(Bean bean);

    List<Bean> filter(Integer tag, Integer type, Integer device, Integer offset);

    List<Bean> findByStatus(String status, int offset);

}
