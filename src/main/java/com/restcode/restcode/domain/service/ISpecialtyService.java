package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.HTMLDocument;
import java.util.Optional;

public interface ISpecialtyService {
    Page<Specialty> getAllSpecialtyByConsultantId(Long consultantId, Pageable pageable);
    Specialty getSpecialtyByIdAndConsultantId(Long specialtyId, Long consultantId);
    Specialty createSpecialty(Long consultantId, Specialty specialty);
    Specialty updateSpecialty(Long consultantId,Long specialtyId,Specialty specialtyRequest);
    ResponseEntity<?> deleteSpecialty(Long consultantId,Long specialtyId);

}
