package com.personal.beans.service;

import com.personal.beans.models.Tag;
import com.personal.beans.repository.postgres.TagRepository;
import com.personal.beans.service.contracts.TagService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.constants.Constants.NAME;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAll() {
        return this.tagRepository.findAll(Sort.by(NAME).ascending());
    }
}
