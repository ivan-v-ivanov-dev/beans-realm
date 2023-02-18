package com.personal.additives.service;

import com.personal.additives.models.Device;
import com.personal.additives.repository.postgres.DeviceRepository;
import com.personal.additives.service.contracts.DeviceService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.additives.constants.Constants.NAME;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> findAllOrderedByNameAsc() {
        return this.deviceRepository.findAll(Sort.by(NAME).ascending());
    }
}
