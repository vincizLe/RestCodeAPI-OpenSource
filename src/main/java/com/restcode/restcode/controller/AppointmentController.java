package com.restcode.restcode.controller;


import com.restcode.restcode.domain.model.Appointment;
import com.restcode.restcode.domain.model.Specialty;
import com.restcode.restcode.domain.service.IAppointmentService;
import com.restcode.restcode.resource.AppointmentResource;
import com.restcode.restcode.resource.SaveAppointmentResource;
import com.restcode.restcode.resource.SaveSpecialtyResource;
import com.restcode.restcode.resource.SpecialtyResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private ModelMapper mapper;




    @GetMapping("consultants/{consultantId}/appointments")
    public Page<AppointmentResource> getAllAppointmentsByConsultantId(@PathVariable(value ="consultantId")Long consultantId, Pageable pageable){
        Page<Appointment> appointmentPage= appointmentService.getAllAppointmentsByConsultantId(consultantId,pageable);
        List<AppointmentResource> resources=appointmentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @PostMapping("consultants/{consultantId}/appointments")
    public AppointmentResource createAppointment( @PathVariable(value = "consultantId") Long consultantId,
                                                 @Valid @RequestBody SaveAppointmentResource resource){
        return  convertToResource(appointmentService.createAppointment(consultantId,convertToEntity(resource)));
    }



    private Appointment convertToEntity(SaveAppointmentResource resource){
        return mapper.map(resource,Appointment.class);
    }

    private AppointmentResource convertToResource(Appointment entity){
        return mapper.map(entity,AppointmentResource.class);
    }
}
