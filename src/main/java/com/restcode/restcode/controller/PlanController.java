package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Plan;
import com.restcode.restcode.domain.service.IPlanService;
import com.restcode.restcode.resource.PlanResource;
import com.restcode.restcode.resource.SavePlanResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name="Plans", description ="Plans API")
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

    @Operation(summary="Get Plan By Consultant Id")
    @GetMapping("/consultants/{consultantId}/plans")
    public PlanResource getPlanByConsultantId(
            @PathVariable(name = "consultantId") Long consultantId) {
        return convertToResource(planService.getPlanByConsultantId(consultantId));
    }

    @Operation(summary="Get Plan By Id")
    @GetMapping("/plans/{planId}")
    public PlanResource getPlanById(@PathVariable(value = "planId") Long planId) {
        return convertToResource(planService.getPlanById(planId));
    }

    @Operation(summary="Create Plan")
    @PostMapping("/plans")
    public PlanResource createPlan(@Valid @RequestBody SavePlanResource resource) {
        return convertToResource(planService.createPlan(convertToEntity(resource)));
    }

    @Operation(summary="Update Plan")
    @PutMapping("/plans/{planId}")
    public PlanResource updatePlan(@PathVariable Long planId,
                                     @Valid @RequestBody SavePlanResource resource) {
        Plan plan = convertToEntity(resource);
        return convertToResource(
                planService.updatePlan(planId, plan));
    }

    @Operation(summary="Delete Plan")
    @DeleteMapping("/plans/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable(name = "id") Long tagId) {
        return planService.deletePlan(tagId);
    }

}
