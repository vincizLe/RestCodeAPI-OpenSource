package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Plan;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.repository.IPlanRepository;
import com.restcode.restcode.domain.service.IPlanService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanService implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private IOwnerRepository ownerRepository;

    //MISSING CONSULTANT
    //private IConsultantRepository consultantRepository;

    @Override
    public Plan getPlanByOwnerId(Long ownerId) {
        return ownerRepository.findById(ownerId).map(owner -> {
            return owner.getPlan();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Owner", "Id", ownerId));
    }

    @Override
    public Plan getPlanByConsultantId(Long consultantId) {
        return null;
        //MISSING CONSULTANT
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
                    plan.setPlanName(planDetails.getPlanName());
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
