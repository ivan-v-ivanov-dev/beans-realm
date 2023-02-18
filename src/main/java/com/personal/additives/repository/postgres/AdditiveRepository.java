package com.personal.additives.repository.postgres;

import com.personal.additives.models.Additive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditiveRepository extends JpaRepository<Additive, Integer> {

}
