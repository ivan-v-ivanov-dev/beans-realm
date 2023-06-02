package com.personal.beans.service;

import com.personal.beans.models.Device;
import com.personal.beans.repository.postgres.DeviceRepository;
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
public class DeviceServiceImplTest {

    @Mock
    Device mockDevice;

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    RedisCacheService redisCacheService;

    @InjectMocks
    DeviceServiceImpl deviceService;

    @Test
    public void findAll_should_retrieveDevicesFromPostgres() {
        List<Device> devices = List.of(mockDevice);
        when(redisCacheService.containsKey(anyString())).thenReturn(false);
        when(deviceRepository.findAll(Sort.by("name").ascending())).thenReturn(devices);

        assertEquals(devices, deviceService.findAll());
    }

    @Test
    public void latest_should_retrieveDevicesFromRedis() {
        when(redisCacheService.containsKey(anyString())).thenReturn(true);
        when(redisCacheService.retrieve(anyString(), any())).thenReturn(List.of(mockDevice));

        assertEquals(mockDevice, deviceService.findAll().get(0));
    }

}
