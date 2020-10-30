package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
    Page<Appointment> findByConsultantId(Long consultantId, Pageable pageable);
    Optional<Appointment> findByIdAndConsultantId(Long appointmentId,Long consultantId);

}
