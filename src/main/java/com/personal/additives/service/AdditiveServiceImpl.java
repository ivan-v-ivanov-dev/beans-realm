package com.personal.additives.service;

import com.personal.additives.models.Additive;
import com.personal.additives.repository.postgres.AdditiveRepository;
import com.personal.additives.service.contracts.AdditiveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditiveServiceImpl implements AdditiveService {

    private final AdditiveRepository additiveRepository;

    public AdditiveServiceImpl(AdditiveRepository additiveRepository) {
        this.additiveRepository = additiveRepository;
    }

    @Override
    public Additive create(Additive additive) {
        return this.additiveRepository.save(additive);
    }

    @Override
    public List<Additive> filter(String additive, String creator, String tag, String type,
                                 String device, int offset) {
        return this.additiveRepository.filter(additive, creator, tag, type, device, offset);
    }
}
