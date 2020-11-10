package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.*;
import com.restcode.restcode.domain.repository.*;
import com.restcode.restcode.domain.service.IAssignmentService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService implements IAssignmentService {
    @Autowired
    private IAssignmentRepository assignmentRepository;

    @Autowired
    private IConsultantRepository consultantRepository;

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public Page<Assignment> getAllAssignmentsByConsultantId(Long consultantId, Pageable pageable) {
        return assignmentRepository.findByConsultantId(consultantId,pageable);
    }

    @Override
    public Page<Assignment> getAllAssignmentsByRestaurantId(Long restaurantId, Pageable pageable) {
        return assignmentRepository.findByRestaurantId(restaurantId,pageable);
    }

    @Override
    public Assignment createAssignment(Long consultantId, Long restaurantId, Assignment assignment) {
        Consultant consultant = consultantRepository.findById(consultantId).orElseThrow(
                ()-> new ResourceNotFoundException("Consultant","Id",consultantId)
        );
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            assignment.setRestaurant(restaurant);
            assignment.setConsultant(consultant);
            return assignmentRepository.save(assignment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Restaurant", "Id", restaurantId));
    }
    @Override
    public Assignment updateAssignment(Long restaurantId, Long assignmentId, Assignment assignmentRequest) {
        return assignmentRepository.findById(assignmentId).map(assignment-> {
            assignment.setState(assignmentRequest.getState());
            return assignmentRepository.save(assignment);
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Assignment", "Id", assignmentId
        ));
    }

    @Override
    public ResponseEntity<?> deleteAssignment(Long restaurantId, Long assignmentId) {
        return assignmentRepository.findById(assignmentId).map(assignment-> {
            assignmentRepository.delete(assignment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Assignment not found with Id " + assignmentId));
    }
}
