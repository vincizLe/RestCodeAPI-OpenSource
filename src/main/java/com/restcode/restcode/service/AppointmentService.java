package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Appointment;
import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.domain.model.Owner;
import com.restcode.restcode.domain.repository.IAppointmentRepository;
import com.restcode.restcode.domain.repository.ICommentRepository;
import com.restcode.restcode.domain.repository.IConsultantRepository;
import com.restcode.restcode.domain.repository.IOwnerRepository;
import com.restcode.restcode.domain.service.IAppointmentService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IConsultantRepository consultantRepository;

    @Autowired
    private IOwnerRepository ownerRepository;

    @Override
    public Page<Appointment> getAllAppointmentsByConsultantId(Long consultantId, Pageable pageable) {
        return appointmentRepository.findByConsultantId(consultantId,pageable);
    }

    @Override
    public Page<Appointment> getAllAppointmentsByOwnerId(Long ownerId, Pageable pageable) {
        return appointmentRepository.findByOwnerId(ownerId,pageable);
    }

    @Override
    public Appointment createAppointment(Long consultantId, Long ownerId, Appointment appointment) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(
                ()-> new ResourceNotFoundException("Owner","Id",ownerId)
        );
        return consultantRepository.findById(consultantId).map(consultant -> {
            appointment.setConsultant(consultant);
            appointment.setOwner(owner);
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Consultant", "Id", consultantId));
    }

    @Override
    public ResponseEntity<?> deleteAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).map(appointment-> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Appointment not found with Id " + appointmentId));
    }
}
