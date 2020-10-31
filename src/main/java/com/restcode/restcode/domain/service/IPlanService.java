package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Plan;
import org.springframework.http.ResponseEntity;

public interface IPlanService {
    Plan getPlanByOwnerId(Long ownerId);
    Plan getPlanByConsultantId(Long consultantId);
    Plan getPlanById(Long planId);
    Plan createPlan(Plan plan);
    Plan updatePlan(Long planId, Plan tagDetails);
    ResponseEntity<?> deletePlan(Long planId);
}
