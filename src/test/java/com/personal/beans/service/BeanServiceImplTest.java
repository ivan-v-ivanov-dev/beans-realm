package com.personal.beans.service;

import com.personal.beans.models.Bean;
import com.personal.beans.models.Version;
import com.personal.beans.repository.postgres.BeanRepository;
import com.personal.beans.repository.postgres.VersionRepository;
import com.personal.beans.service.contracts.EntityMapperService;
import com.personal.beans.service.contracts.RedisCacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeanServiceImplTest {

    @Mock
    Bean mockBean;

    @Mock
    BeanRepository beanRepository;

    @Mock
    RedisCacheService redisCacheService;

    @Mock
    VersionRepository versionRepository;

    @Mock
    EntityMapperService entityMapperService;

    @InjectMocks
    BeanServiceImpl beanService;

    @Test
    public void latest_should_retrieveDataFromPostgres() {
        List<Bean> mockBeans = List.of(mockBean);
        when(mockBeans.get(0).getName()).thenReturn("Name");
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(beanRepository.latest()).thenReturn(mockBeans);
        when(versionRepository.beanDownloadCount(anyString())).thenReturn(1);

        assertEquals(mockBeans, beanService.latest());
    }

    @Test
    public void latest_should_retrieveDataFromRedis() {
        when(redisCacheService.containsKey(anyString())).thenReturn(true);
        when(redisCacheService.retrieve(anyString(), any())).thenReturn(List.of(mockBean));

        assertEquals(mockBean, beanService.latest().get(0));
    }

    @Test
    public void mostDownloaded_should_retrieveDataFromPostgres() {
        List<Bean> mockBeans = List.of(mockBean);
        when(mockBeans.get(0).getName()).thenReturn("Name");
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(beanRepository.mostDownloaded()).thenReturn(mockBeans);
        when(versionRepository.beanDownloadCount(anyString())).thenReturn(1);

        assertEquals(mockBeans, beanService.mostDownloaded());
    }

    @Test
    public void mostDownloaded_should_retrieveDataFromRedis() {
        when(redisCacheService.containsKey(anyString())).thenReturn(true);
        when(redisCacheService.retrieve(anyString(), any())).thenReturn(List.of(mockBean));

        assertEquals(mockBean, beanService.mostDownloaded().get(0));
    }

    @Test
    public void topRated_should_retrieveDataFromPostgres() {
        List<Bean> mockBeans = List.of(mockBean);
        when(mockBeans.get(0).getName()).thenReturn("Name");
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(beanRepository.topRated()).thenReturn(mockBeans);
        when(versionRepository.beanDownloadCount(anyString())).thenReturn(1);

        assertEquals(mockBeans, beanService.topRated());
    }

    @Test
    public void topRated_should_retrieveDataFromRedis() {
        when(redisCacheService.containsKey(anyString())).thenReturn(true);
        when(redisCacheService.retrieve(anyString(), any())).thenReturn(List.of(mockBean));

        assertEquals(mockBean, beanService.topRated().get(0));
    }

    @Test
    public void beansCount_should_retrieveDataFromPostgres() {
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(beanRepository.beansCount()).thenReturn(1);

        assertEquals(1, beanService.beansCount());
    }

    @Test
    public void beansCount_should_retrieveDataFromRedis() {
        when(redisCacheService.containsKey(anyString())).thenReturn(true);
        when(redisCacheService.retrieve(anyString(), any())).thenReturn(List.of(1));

        assertEquals(1, beanService.beansCount());
    }

    @Test
    public void findByName_should_retrieveDataFromPostgres() {
        when(mockBean.getName()).thenReturn("Name");
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(beanRepository.findByName(anyString())).thenReturn(mockBean);

        assertEquals(mockBean, beanService.findByName(anyString()));
    }

    @Test
    public void create_should_callBeanRepository() throws IOException {
        when(entityMapperService.toBean(any())).thenReturn(mock(Bean.class));
        beanService.create(any());
        verify(beanRepository, times(1)).save(any());
    }

    @Test
    public void create_should_callVersionRepository() throws IOException, NoSuchAlgorithmException {
        when(entityMapperService.toBean(any())).thenReturn(mock(Bean.class));
        when(entityMapperService.toFirstVersion(any(), any())).thenReturn(mock(Version.class));
        beanService.create(any());

        verify(versionRepository, times(1)).save(any());
    }

    @Test
    public void filter_should_callRepository() {
        beanService.filter(any(), any(), any(), any());
        verify(beanRepository, times(1)).filter(any(), any(), any(), any());
    }

    @Test
    public void notApproved_should_retrieveData() {
        List<Bean> mockBeans = List.of(mockBean);
        when(mockBeans.get(0).getName()).thenReturn("Name");
        when(beanRepository.notApproved()).thenReturn(mockBeans);
        when(versionRepository.beanDownloadCount(anyString())).thenReturn(1);

        assertEquals(mockBeans, beanService.notApproved());
    }
}
