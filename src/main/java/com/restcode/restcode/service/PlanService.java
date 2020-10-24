package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Plan;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.repository.IPlanRepository;
import com.restcode.restcode.domain.service.IPlanService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;


public class PlanService implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private IOwnerRepository ownerRepository;

    //MISSING CONSULTANT
    //private IConsultantRepository consultantRepository;

    @Override
    public Plan getPlanByOwnerId(Long ownerId, Long planId) {
        return ownerRepository.findByIdAndOwnerId(planId, ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with Id " + planId +
                                " and OwnerId " + ownerId));
    }

    @Override
    public Plan getPlanByConsultantId(Long consultantId, Long planId) {
        return null;
        //MISSING CONSULTANT
    }

}
