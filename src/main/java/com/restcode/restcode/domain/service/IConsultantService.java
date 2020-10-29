package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Consultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IConsultantService {
    Page<Consultant> getAllConsultants(Pageable pageable);
    Consultant getConsultantById(Long consultantId);
    Consultant createConsultant(Consultant consultant);
    Consultant updateConsultant(Long consultantId,Consultant consultantRequest);
    ResponseEntity<?> deleteConsultant(Long consultantId);
}
