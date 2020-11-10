package com.restcode.restcode.controller;


import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.service.IOwnerService;
import com.restcode.restcode.resource.ConsultantResource;
import com.restcode.restcode.resource.OwnerResource;
import com.restcode.restcode.resource.SaveConsultantResource;
import com.restcode.restcode.resource.SaveOwnerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="Owners", description ="Owners API")
@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IOwnerService ownerService;

    private Owner convertToEntity(SaveOwnerResource resource) {
        return mapper.map(resource, Owner.class);
    }
    private OwnerResource convertToResource(Owner entity) {
        return mapper.map(entity, OwnerResource.class);
    }

    @Operation(summary="Get All Owners")
    @GetMapping("/owners")
    public Page<OwnerResource> getAllOwners(Pageable pageable) {

        Page<Owner> ownersPage = ownerService.getAllOwners(pageable);
        List<OwnerResource> resources = ownersPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get Owner")
    @GetMapping("/owners/{ownerId}")
    public OwnerResource getOwnerById(@PathVariable(value = "ownerId") Long ownerId) {
        return convertToResource(ownerService.getOwnerById(ownerId));
    }

    @Operation(summary="Create Owner")
    @PostMapping("plans/{planId}/owners")
    public OwnerResource createOwner(@PathVariable(value = "planId") Long planId,
                                               @Valid @RequestBody SaveOwnerResource resource){
        return convertToResource(ownerService.createOwner(planId,convertToEntity(resource)));
    }


    @Operation(summary="Update Owner")
    @PutMapping("/owners/{ownerId}")
    public OwnerResource updateOwner(@PathVariable Long ownerId,
                                   @Valid @RequestBody SaveOwnerResource resource) {
        Owner owner = convertToEntity(resource);
        return convertToResource(
                ownerService.updateOwner(ownerId, owner));
    }

    @Operation(summary="Assign Plan To Owner")
    @PostMapping("/posts/{ownerId}/plans/{planId}")
    public OwnerResource assignOwnerPlan(
            @PathVariable(name = "ownerId") Long ownerId,
            @PathVariable(name = "planId") Long planId) {
        return convertToResource(ownerService.assignOwnerPlan(ownerId, planId));
    }
}
