package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Plan;
//import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.model.Restaurant;
import com.restcode.restcode.domain.repository.IConsultantRepository;
import com.restcode.restcode.domain.repository.IPlanRepository;
import com.restcode.restcode.domain.service.IPlanService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanService implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public Page<Plan> getAllPlans(Pageable pageable) {
        return planRepository.findAll(pageable);
    }

    @Override
    public Plan getPlanById(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plan", "Id", planId));
    }

    @Override
    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan updatePlan(Long planId, Plan planDetails) {
        return planRepository.findById(planId)
                .map(plan -> {
                    plan.setName(planDetails.getName());
                    plan.setPrice(planDetails.getPrice());
                    return planRepository.save(plan);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "PlanId " + planId + " not found"
                ));
    }

    @Override
    public ResponseEntity<?> deletePlan(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plan", "Id", planId));
        planRepository.delete(plan);
        return ResponseEntity.ok().build();
    }

}
