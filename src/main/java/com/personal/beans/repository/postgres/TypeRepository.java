package com.personal.beans.repository.postgres;

import com.personal.beans.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
