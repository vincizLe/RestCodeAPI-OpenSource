package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Appointment;
import com.restcode.restcode.domain.repository.IAppointmentRepository;
import com.restcode.restcode.domain.repository.IConsultantRepository;
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
   private IConsultantRepository consultantRepository;

   @Autowired
   private IAppointmentRepository appointmentRepository;

    @Override
    public Page<Appointment> getAllAppointmentsByConsultantId(Long consultantId, Pageable pageable) {
        return appointmentRepository.findByConsultantId(consultantId,pageable);
    }

    @Override
    public Appointment getAppointmentByIdAndConsultantId(Long appointmentId, Long consultantId) {
        return appointmentRepository.findByIdAndConsultantId(appointmentId,consultantId)
                .orElseThrow(()->new ResourceNotFoundException("" +
                        "Appointment not found with Id"+ appointmentId + "and ConsultantId" + consultantId));
    }

    @Override
    public Appointment createAppointment(Long consultantId, Appointment appointment) {
        return consultantRepository.findById(consultantId).map(consultant -> {
            appointment.setConsultant(consultant);
            return appointmentRepository.save(appointment);
        }).orElseThrow(()->new ResourceNotFoundException("Consultant","Id",consultantId));
    }

    @Override
    public Appointment updateAppointment(Long consultantId, Long specialtyId, Appointment appointmentRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSpecialty(Long consultantId, Long appointmentId) {
        return null;
    }
}
