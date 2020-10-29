package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Specialty;
import com.restcode.restcode.domain.repository.IConsultantRepository;
import com.restcode.restcode.domain.repository.ISpecialtyRepository;
import com.restcode.restcode.domain.service.ISpecialtyService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class SpecialtyService implements ISpecialtyService {


    @Autowired
    private IConsultantRepository consultantRepository;

    @Autowired
    private ISpecialtyRepository specialtyRepository;



    @Override
    public Page<Specialty> getAllSpecialtyByConsultantId(Long consultantId, Pageable pageable) {
        return specialtyRepository.findByConsultantId(consultantId,pageable);
    }

    @Override
    public Specialty getSpecialtyByIdAndConsultantId(Long specialtyId, Long consultantId) {
        return specialtyRepository.findByIdAndConsultantId(specialtyId,consultantId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Specialty not found with Id" + specialtyId +
                                "and ConsultantId" + consultantId
                ));
    }

    @Override
    public Specialty createSpecialty(Long consultantId, Specialty specialty) {
        return consultantRepository.findById(consultantId).map(consultant -> {
            specialty.setConsultant(consultant);
            return specialtyRepository.save(specialty);
        }).orElseThrow(()->new ResourceNotFoundException("Consultant","Id",consultantId));
    }

    @Override
    public Specialty updateSpecialty(Long consultantId, Long specialtyId, Specialty specialtyRequest) {
        if(!consultantRepository.existsById(consultantId))
            throw  new ResourceNotFoundException("Consultant","Id",consultantId);

        return specialtyRepository.findById(specialtyId).map(specialty -> {
            specialty.setName(specialtyRequest.getName());
            specialty.setDescription(specialtyRequest.getDescription());
            specialty.setStudyInstitution(specialtyRequest.getStudyInstitution());
            return specialtyRepository.save(specialty);
        }).orElseThrow(()->new ResourceNotFoundException("Specialty", "Id",specialtyId));

    }

    @Override
    public ResponseEntity<?> deleteSpecialty(Long consultantId, Long specialtyId) {
        return specialtyRepository.findByIdAndConsultantId(specialtyId,consultantId).map(specialty -> {
            specialtyRepository.delete(specialty);
            return  ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Specialty not found by Id" + specialtyId + "and ConsultantId" + consultantId
        ));
    }
}
