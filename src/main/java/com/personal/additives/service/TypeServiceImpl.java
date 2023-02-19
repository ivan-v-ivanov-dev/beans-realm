package com.personal.additives.service;

import com.personal.additives.models.Type;
import com.personal.additives.repository.postgres.TypeRepository;
import com.personal.additives.service.contracts.TypeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.additives.constants.Constants.NAME;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> findAll() {
        return this.typeRepository.findAll(Sort.by(NAME).ascending());
    }
}
