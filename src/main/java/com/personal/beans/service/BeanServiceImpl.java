package com.personal.beans.service;

import com.personal.beans.models.Bean;
import com.personal.beans.repository.postgres.BeanRepository;
import com.personal.beans.service.contracts.BeanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeanServiceImpl implements BeanService {

    private final BeanRepository beanRepository;

    public BeanServiceImpl(BeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    @Override
    public List<Bean> latest() {
        return this.beanRepository.latest();
    }

    @Override
    public List<Bean> mostDownloaded() {
        return this.beanRepository.mostDownloaded();
    }

    @Override
    public List<Bean> topRated() {
        return this.beanRepository.topRated();
    }

    @Override
    public int beansCount() {
        return this.beanRepository.beansCount();
    }

    @Override
    public Bean create(Bean bean) {
        return this.beanRepository.save(bean);
    }

    @Override
    public List<Bean> filter(String bean, String creator, String tag, String type,
                             String device, int offset) {
        return this.beanRepository.filter(bean, creator, tag, type, device, offset);
    }

    @Override
    public List<Bean> findByStatus(String status, int offset) {
        return this.beanRepository.findByStatus(status, offset);
    }
}
