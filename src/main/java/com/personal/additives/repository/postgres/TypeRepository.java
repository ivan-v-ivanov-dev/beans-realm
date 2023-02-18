package com.personal.additives.repository.postgres;

import com.personal.additives.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
