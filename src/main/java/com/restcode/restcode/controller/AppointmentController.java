package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Appointment;
import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.resource.AppointmentResource;
import com.restcode.restcode.resource.CommentResource;
import com.restcode.restcode.resource.SaveAppointmentResource;
import com.restcode.restcode.resource.SaveCommentResource;
import com.restcode.restcode.service.AppointmentService;
import com.restcode.restcode.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="Appointments", description ="Appointment API")
@RestController
@RequestMapping("/api")
public class AppointmentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AppointmentService appointmentService;

    private Appointment convertToEntity(SaveAppointmentResource resource){
        return mapper.map(resource,Appointment.class);
    }

    private AppointmentResource convertToResource(Appointment entity){
        return mapper.map(entity,AppointmentResource.class);
    }

    @Operation(summary="Get All Appointments By Consultant Id")
    @GetMapping("consultants/{consultantId}/appointments")
    public Page<AppointmentResource> getAllAppointmentsByConsultantId(
            @PathVariable(value = "consultantId") Long consultantId, Pageable pageable){
        Page<Appointment> appointmentPage = appointmentService.getAllAppointmentsByConsultantId(consultantId,pageable);
        List<AppointmentResource> resources = appointmentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary="Get All Appointments By Owner Id")
    @GetMapping("owners/{ownerId}/appointments")
    public Page<AppointmentResource> getAllAppointmentsByOwnerId(
            @PathVariable (value = "ownerId") Long ownerId, Pageable pageable){
        Page<Appointment> appointmentPage = appointmentService.getAllAppointmentsByOwnerId(ownerId,pageable);
        List<AppointmentResource> resources = appointmentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }


    @Operation(summary="Create Appointment")
    @PostMapping("owners/{ownerId}/consultants/{consultantId}/appointments")
    public AppointmentResource createAppointment(
            @PathVariable(value = "consultantId") Long consultantId,
            @PathVariable(value = "ownerId") Long ownerId,
            @Valid @RequestBody SaveAppointmentResource resource) {
        return convertToResource(appointmentService.createAppointment(consultantId,ownerId,convertToEntity(resource)));
    }

    @Operation(summary="Delete Appointment")
    @DeleteMapping("appointments/{appointmentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable (value = "appointmentId") Long appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }
}
