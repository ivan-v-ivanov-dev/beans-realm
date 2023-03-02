package com.personal.beans.service.contracts;

import com.personal.beans.models.Bean;
import com.personal.beans.models.dto.BeanDto;

import java.util.List;

public interface BeanService {

    List<Bean> latest();

    List<Bean> mostDownloaded();

    List<Bean> topRated();

    int beansCount();

    Bean findByName(String beanName);

    void create(BeanDto beanDto);

    List<Bean> filter(Integer tag, Integer type, Integer device, Integer offset);

    List<Bean> findByStatus(String status, int offset);

}
