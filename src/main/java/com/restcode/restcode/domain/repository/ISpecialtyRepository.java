package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISpecialtyRepository extends JpaRepository<Specialty,Long> {

    Page<Specialty> findByConsultantId(Long consultantId, Pageable pageable);
    Optional<Specialty> findByIdAndConsultantId(Long specialtyId,Long consultantId);
}
