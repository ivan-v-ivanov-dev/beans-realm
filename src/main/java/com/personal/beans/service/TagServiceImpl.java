package com.personal.beans.service;

import com.personal.beans.models.Tag;
import com.personal.beans.repository.postgres.TagRepository;
import com.personal.beans.service.contracts.RedisCacheService;
import com.personal.beans.service.contracts.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.constants.Constants.*;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final RedisCacheService redisCacheService;

    public TagServiceImpl(TagRepository tagRepository,
                          RedisCacheService redisCacheService) {
        this.tagRepository = tagRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public List<Tag> findAll() {
        if (this.redisCacheService.containsKey(ALL_TAGS)) {
            log.info(FROM_REDIS_ALL_TAGS);
            return this.redisCacheService.retrieve(ALL_TAGS, Tag.class);
        }

        List<Tag> tags = this.tagRepository.findAll(Sort.by(NAME).ascending());
        this.redisCacheService.save(ALL_TAGS, tags);
        log.info(FROM_POSTGRES_ALL_TAGS);
        return tags;
    }
}
