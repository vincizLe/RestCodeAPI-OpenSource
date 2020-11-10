package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Plan;
import com.restcode.restcode.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IPlanService {
    Page<Plan> getAllPlans(Pageable pageable);
    Plan getPlanById(Long planId);
    Plan createPlan(Plan plan);
    Plan updatePlan(Long planId, Plan tagDetails);
    ResponseEntity<?> deletePlan(Long planId);
}
