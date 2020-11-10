package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IAssignmentService {
    Page<Assignment> getAllAssignmentsByConsultantId(Long consultantId, Pageable pageable);
    Page<Assignment> getAllAssignmentsByRestaurantId(Long restaurantId, Pageable pageable);
    Assignment createAssignment(Long consultantId, Long restaurantId, Assignment assignment);
    Assignment updateAssignment(Long restaurantId,Long assignmentId ,Assignment assignmentRequest);
    ResponseEntity<?> deleteAssignment(Long restaurantId, Long assignmentId);
}
