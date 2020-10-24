package com.restcode.restcode.controller;


import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.service.IOwnerService;
import com.restcode.restcode.resource.OwnerResource;
import com.restcode.restcode.resource.SaveOwnerResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/owners")
    public Page<OwnerResource> getAllOwners(Pageable pageable) {

        Page<Owner> ownersPage = ownerService.getAllOwners(pageable);
        List<OwnerResource> resources = ownersPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/owners/{ownerId}")
    public OwnerResource getOwnerById(@PathVariable(value = "ownerId") Long ownerId) {
        return convertToResource(ownerService.getOwnerById(ownerId));
    }

    @PostMapping("/owners")
    public OwnerResource createOwner(
            @Valid @RequestBody SaveOwnerResource resource) {
        Owner owner = convertToEntity(resource);
        return convertToResource(ownerService.createOwner(owner));
    }

    @PutMapping("/owners/{ownerId}")
    public OwnerResource updateOwner(@PathVariable Long ownerId,
                                   @Valid @RequestBody SaveOwnerResource resource) {
        Owner owner = convertToEntity(resource);
        return convertToResource(
                ownerService.updateOwner(ownerId, owner));
    }
}
