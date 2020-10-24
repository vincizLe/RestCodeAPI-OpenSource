package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanRepository extends JpaRepository<Plan, Long> {
}
