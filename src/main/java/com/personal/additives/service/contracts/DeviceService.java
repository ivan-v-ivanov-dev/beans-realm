package com.personal.additives.service.contracts;

import com.personal.additives.models.Device;

import java.util.List;

public interface DeviceService {

    List<Device> findAllOrderedByNameAsc();
}
