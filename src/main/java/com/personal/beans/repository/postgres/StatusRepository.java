package com.personal.beans.repository.postgres;

import com.personal.beans.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByName(String waiting_approval);
}
