package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Plan;
import org.springframework.http.ResponseEntity;

public interface IPlanService {
    Plan getPlanByOwnerId(Long ownerId, Long planId);
    Plan getPlanByConsultantId(Long consultantId, Long planId);

}
