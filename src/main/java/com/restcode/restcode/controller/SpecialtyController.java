package com.restcode.restcode.controller;


import com.restcode.restcode.domain.model.Specialty;
import com.restcode.restcode.domain.service.ISpecialtyService;
import com.restcode.restcode.resource.SaveSpecialtyResource;
import com.restcode.restcode.resource.SpecialtyResource;
import com.restcode.restcode.service.SpecialtyService;
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

@Tag(name="Specialties", description ="Specialty API")
@RestController
@RequestMapping("/api")
public class SpecialtyController {
    @Autowired
    private ISpecialtyService specialtyService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Specialties by Consultant Id")
    @GetMapping("consultants/{consultantId}/specialties")
    public Page<SpecialtyResource> getAllSpecialtiesByConsultantId(
            @PathVariable(value ="consultantId")Long consultantId, Pageable pageable){
        Page<Specialty> specialtyPage=specialtyService.getAllSpecialtyByConsultantId(consultantId,pageable);
        List<SpecialtyResource> resources=specialtyPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Create Specialty")
    @PostMapping("/consultants/{consultantId}/specialties")
    public SpecialtyResource createSpecialty(
            @PathVariable(value = "consultantId") Long consultantId,
            @Valid @RequestBody SaveSpecialtyResource resource){
        return convertToResource(specialtyService.createSpecialty(consultantId,convertToEntity(resource)));
    }

    private Specialty convertToEntity(SaveSpecialtyResource resource){
        return mapper.map(resource,Specialty.class);
    }

    private SpecialtyResource convertToResource(Specialty  entity){
        return mapper.map(entity,SpecialtyResource.class);
    }

}
