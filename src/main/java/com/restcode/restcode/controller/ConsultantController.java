package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Consultant;
import com.restcode.restcode.resource.*;
import com.restcode.restcode.service.ConsultantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="Consultants", description ="Consultants API")
@RestController
@RequestMapping("/api")
public class ConsultantController {

   @Autowired
   private ModelMapper mapper;

   @Autowired
   private ConsultantService consultantService;

   @Operation(summary="Get All Consultants")
   @GetMapping("/consultants")
   public Page<ConsultantResource> getAllConsultants(Pageable pageable){
       Page<Consultant> consultantsPage= consultantService.getAllConsultants(pageable);
       List<ConsultantResource> resources = consultantsPage.getContent()
               .stream().map(this::convertToResource)
               .collect(Collectors.toList());
       return new PageImpl<>(resources,pageable, resources.size());
   }

   @Operation(summary="Get Consultants By Id")
   @GetMapping("consultants/{consultantId}")
   public ConsultantResource getConsultantById(@PathVariable(value = "consultantId") Long consultantId){
       return convertToResource(consultantService.getConsultantById(consultantId));
   }


   @Operation(summary="Create Consultant")
   @PostMapping("plans/{planId}/consultants")
   public ConsultantResource createConsultant(@PathVariable(value = "planId") Long planId,
                                              @Valid @RequestBody SaveConsultantResource resource){
       return convertToResource(consultantService.createConsultant(planId,convertToEntity(resource)));
   }

   @Operation(summary="Delete Consultant")
   @DeleteMapping("/consultants/{consultantId}")
   public ResponseEntity<?> deleteConsultant(@PathVariable Long consultantId){
       return  consultantService.deleteConsultant(consultantId);
   }


   private Consultant convertToEntity(SaveConsultantResource resource){
       return mapper.map(resource,Consultant.class);
   }

   private ConsultantResource convertToResource(Consultant entity){
       return mapper.map(entity,ConsultantResource.class);
   }
}
