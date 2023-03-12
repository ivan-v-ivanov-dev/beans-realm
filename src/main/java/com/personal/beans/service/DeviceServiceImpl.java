package com.personal.beans.service;

import com.personal.beans.models.Device;
import com.personal.beans.repository.postgres.DeviceRepository;
import com.personal.beans.service.contracts.DeviceService;
import com.personal.beans.service.contracts.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.service.constants.LoggerConstants.FROM_POSTGRES_ALL_DEVICES;
import static com.personal.beans.service.constants.LoggerConstants.FROM_REDIS_ALL_DEVICES;
import static com.personal.beans.service.constants.RedisKeysConstants.ALL_DEVICES;
import static com.personal.beans.service.constants.ServiceConstants.NAME;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final RedisCacheService redisCacheService;

    public DeviceServiceImpl(DeviceRepository deviceRepository,
                             RedisCacheService redisCacheService) {
        this.deviceRepository = deviceRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public List<Device> findAll() {
        if (this.redisCacheService.containsKey(ALL_DEVICES)) {
            log.info(FROM_REDIS_ALL_DEVICES);
            return this.redisCacheService.retrieve(ALL_DEVICES, Device.class);
        }

        List<Device> devices = this.deviceRepository.findAll(Sort.by(NAME).ascending());
        this.redisCacheService.save(ALL_DEVICES, devices);
        log.info(FROM_POSTGRES_ALL_DEVICES);
        return devices;
    }
}
