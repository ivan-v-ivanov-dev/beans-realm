package com.personal.additives.service;

import com.personal.additives.models.Tag;
import com.personal.additives.repository.postgres.TagRepository;
import com.personal.additives.service.contracts.TagService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.additives.constants.Constants.NAME;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAllOrderedByNameAsc() {
        return this.tagRepository.findAll(Sort.by(NAME).ascending());
    }
}
