package com.personal.additives.service;

import com.personal.additives.repository.postgres.AdditiveRepository;
import com.personal.additives.service.contracts.AdditiveService;
import org.springframework.stereotype.Service;

@Service
public class AdditiveServiceImpl implements AdditiveService {

    private final AdditiveRepository additiveRepository;

    public AdditiveServiceImpl(AdditiveRepository additiveRepository) {
        this.additiveRepository = additiveRepository;
    }

}
