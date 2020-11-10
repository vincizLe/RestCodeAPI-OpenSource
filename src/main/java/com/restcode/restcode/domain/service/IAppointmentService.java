package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Appointment;
import com.restcode.restcode.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IAppointmentService {
    Page<Appointment> getAllAppointmentsByConsultantId(Long consultantId, Pageable pageable);
    Page<Appointment> getAllAppointmentsByOwnerId(Long ownerId, Pageable pageable);
    Appointment createAppointment(Long consultantId, Long ownerId, Appointment appointment);
    ResponseEntity<?> deleteAppointment(Long appointmentId);
}
