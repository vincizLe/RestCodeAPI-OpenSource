package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Consultant;
import com.restcode.restcode.domain.repository.IConsultantRepository;
import com.restcode.restcode.domain.service.IConsultantService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService implements IConsultantService {


    @Autowired
    private IConsultantRepository consultantRepository;


    @Override
    public Page<Consultant> getAllConsultants(Pageable pageable) {
        return consultantRepository.findAll(pageable);
    }

    @Override
    public Consultant getConsultantById(Long consultantId) {
        return consultantRepository.findById(consultantId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Consultant","Id",consultantId
                ));
    }

    @Override
    public Consultant createConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public Consultant updateConsultant(Long consultantId, Consultant consultantRequest) {

        return consultantRepository.findById(consultantId).map(consultant -> {
           consultant.setSurnames(consultantRequest.getSurnames());
           consultant.setNames(consultantRequest.getNames());
           consultant.setEmail(consultantRequest.getEmail());
           consultant.setPassword(consultantRequest.getPassword());
           consultant.setPhone(consultantRequest.getPhone());
           consultant.setLinkedln(consultantRequest.getLinkedln());
           return consultantRepository.save(consultant);
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Consultant", "Id", consultantId
        ));

    }

    @Override
    public ResponseEntity<?> deleteConsultant(Long consultantId) {
        return consultantRepository.findById(consultantId).map(consultant -> {
           consultantRepository.delete(consultant);
           return ResponseEntity.ok().build();
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Consultant", "Id", consultantId
        ));
    }
}
