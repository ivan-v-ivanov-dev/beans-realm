package com.personal.additives.repository.postgres;

import com.personal.additives.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
