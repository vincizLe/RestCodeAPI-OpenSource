package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Plan;
import com.restcode.restcode.domain.service.IPlanService;
import com.restcode.restcode.resource.PlanResource;
import com.restcode.restcode.resource.SavePlanResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlanController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IPlanService planService;

    private Plan convertToEntity(SavePlanResource resource) {
        return mapper.map(resource, Plan.class);
    }
    private PlanResource convertToResource(Plan entity) {
        return mapper.map(entity, PlanResource.class);
    }


    @GetMapping("/owners/{ownerId}/plan/{planId}")
    public PlanResource getPlanByOwnerId(
            @PathVariable(name = "ownerId") Long ownerId,
            @PathVariable(name = "planId") Long planId) {
        return convertToResource(planService.getPlanByOwnerId(ownerId, planId));
    }

    @GetMapping("/consultants/{consultantId}/plan/{planId}")
    public PlanResource getPlanByConsultantId(
            @PathVariable(name = "consultantId") Long consultantId,
            @PathVariable(name = "planId") Long planId) {
        return convertToResource(planService.getPlanByConsultantId(consultantId, planId));
    }

}
