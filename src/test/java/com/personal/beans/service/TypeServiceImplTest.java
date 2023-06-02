package com.personal.beans.service;

import com.personal.beans.models.Type;
import com.personal.beans.repository.postgres.TypeRepository;
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
public class TypeServiceImplTest {

    @Mock
    Type mockType;

    @Mock
    TypeRepository typeRepository;

    @Mock
    RedisCacheService redisCacheService;

    @InjectMocks
    TypeServiceImpl typeService;

    @Test
    public void findAll_should_retrieveTypesFromPostgres() {
        List<Type> types = List.of(mockType);
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(typeRepository.findAll(Sort.by("name").ascending())).thenReturn(types);

        assertEquals(types, typeService.findAll());
    }

    @Test
    public void latest_should_retrieveTypesFromRedis() {
        when(redisCacheService.containsKey(anyString())).thenReturn(true);
        when(redisCacheService.retrieve(anyString(), any())).thenReturn(List.of(mockType));

        assertEquals(mockType, typeService.findAll().get(0));
    }
}
