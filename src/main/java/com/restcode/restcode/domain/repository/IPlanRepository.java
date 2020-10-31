package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlanRepository extends JpaRepository<Plan, Long> {
    //Optional<Plan> findByIdAndOwnerId(Long planId, Long ownerId);
}
