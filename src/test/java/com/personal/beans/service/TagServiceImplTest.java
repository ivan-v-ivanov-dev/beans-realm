package com.personal.beans.service;

import com.personal.beans.models.Tag;
import com.personal.beans.repository.postgres.TagRepository;
import com.personal.beans.service.contracts.RedisCacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {

    @Mock
    Tag mockTag;

    @Mock
    TagRepository tagRepository;

    @Mock
    RedisCacheService redisCacheService;

    @InjectMocks
    TagServiceImpl tagService;

    @Test
    public void findAll_should_retrieveTagsFromPostgres() {
        List<Tag> tags = List.of(mockTag);
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(tagRepository.findAll(Sort.by("name").ascending())).thenReturn(tags);

        assertEquals(tags, tagService.findAll());
    }

    @Test
    public void latest_should_retrieveTagsFromRedis() {
        when(redisCacheService.containsKey(anyString())).thenReturn(true);
        when(redisCacheService.retrieve(anyString(), any())).thenReturn(List.of(mockTag));

        assertEquals(mockTag, tagService.findAll().get(0));
    }
}
