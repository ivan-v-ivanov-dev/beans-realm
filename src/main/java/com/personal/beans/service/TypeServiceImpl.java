package com.personal.beans.service;

import com.personal.beans.models.Type;
import com.personal.beans.repository.postgres.TypeRepository;
import com.personal.beans.service.contracts.TypeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.constants.Constants.NAME;

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
