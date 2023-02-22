package com.personal.beans.service;

import com.personal.beans.models.Device;
import com.personal.beans.repository.postgres.DeviceRepository;
import com.personal.beans.service.contracts.DeviceService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.beans.constants.Constants.NAME;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> findAll() {
        return this.deviceRepository.findAll(Sort.by(NAME).ascending());
    }
}
