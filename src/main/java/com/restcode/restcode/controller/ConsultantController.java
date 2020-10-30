package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Consultant;
import com.restcode.restcode.resource.ConsultantResource;
import com.restcode.restcode.resource.SaveConsultantResource;
import com.restcode.restcode.service.ConsultantService;
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

@RestController
@RequestMapping("/api")
public class ConsultantController {

   @Autowired
   private ModelMapper mapper;

   @Autowired
   private ConsultantService consultantService;

   @GetMapping("/consultants")
   public Page<ConsultantResource> getAllConsultants(Pageable pageable){
       Page<Consultant> consultantsPage= consultantService.getAllConsultants(pageable);
       List<ConsultantResource> resources = consultantsPage.getContent()
               .stream().map(this::convertToResource)
               .collect(Collectors.toList());
       return new PageImpl<>(resources,pageable, resources.size());
   }


   @GetMapping("consultants/{consultantId}")
   public ConsultantResource getConsultantById(@PathVariable(value = "consultantId") Long consultantId){
       return convertToResource(consultantService.getConsultantById(consultantId));
   }



   @PostMapping("/Consultant")
   public ConsultantResource createConsultant(@Valid @RequestBody SaveConsultantResource resource){
       Consultant consultant=convertToEntity(resource);
       return  convertToResource(consultantService.createConsultant(consultant));
   }

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
