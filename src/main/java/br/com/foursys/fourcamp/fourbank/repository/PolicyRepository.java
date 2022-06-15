package com.fourcamp.fourbank.repository;

import com.fourcamp.fourbank.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
