package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Appointment;
import com.restcode.restcode.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IAppointmentService {
    Page<Appointment> getAllAppointmentsByConsultantId(Long consultantId, Pageable pageable);
    Appointment getAppointmentByIdAndConsultantId(Long appointmentId, Long consultantId);
    Appointment createAppointment(Long consultantId, Appointment appointment);
    Appointment updateAppointment(Long consultantId,Long specialtyId,Appointment appointmentRequest);
    ResponseEntity<?> deleteSpecialty(Long consultantId, Long appointmentId);
}
