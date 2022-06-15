package com.fourcamp.fourbank.repository;

import com.fourcamp.fourbank.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
