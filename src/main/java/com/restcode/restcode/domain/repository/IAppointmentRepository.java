package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Appointment;
import com.restcode.restcode.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findByConsultantId(Long consultantId, Pageable pageable);
    Page<Appointment> findByOwnerId(Long ownerId, Pageable pageable);
}
