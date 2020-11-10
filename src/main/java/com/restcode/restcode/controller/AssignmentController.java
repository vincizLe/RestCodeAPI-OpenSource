package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Assignment;
import com.restcode.restcode.domain.model.Comment;
import com.restcode.restcode.domain.model.Restaurant;
import com.restcode.restcode.resource.*;
import com.restcode.restcode.service.AssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="Assignments", description ="Assignment API")
@RestController
@RequestMapping("/api")
public class AssignmentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AssignmentService assignmentService;

    private Assignment convertToEntity(SaveAssignmentResource resource){
        return mapper.map(resource,Assignment.class);
    }

    private AssignmentResource convertToResource(Assignment entity){
        return mapper.map(entity,AssignmentResource.class);
    }

    @Operation(summary="Get All Assignments By Consultant Id")
    @GetMapping("consultants/{consultantId}/assignments")
    public Page<AssignmentResource> getAllAssignmentsByConsultantId(
            @PathVariable(value = "consultantId") Long consultantId, Pageable pageable){
        Page<Assignment> assignmentPage = assignmentService.getAllAssignmentsByConsultantId(consultantId,pageable);
        List<AssignmentResource> resources = assignmentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary="Get All Assignments By Restaurant Id")
    @GetMapping("restaurants/{restaurantId}/assignments")
    public Page<AssignmentResource> getAllAssignmentsByRestaurantId(
            @PathVariable (value = "restaurantId") Long restaurantId, Pageable pageable){
        Page<Assignment> assignmentPage = assignmentService.getAllAssignmentsByRestaurantId(restaurantId,pageable);
        List<AssignmentResource> resources = assignmentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }


    @Operation(summary="Create Assignment")
    @PostMapping("restaurants/{restaurantId}/consultants/{consultantId}/assignments")
    public AssignmentResource createAssignment(
            @PathVariable(value = "consultantId") Long consultantId,
            @PathVariable(value = "restaurantId") Long restaurantId,
            @Valid @RequestBody SaveAssignmentResource resource) {
        return convertToResource(assignmentService.createAssignment(consultantId,restaurantId,convertToEntity(resource)));
    }

    @Operation(summary="Update Assignment")
    @PutMapping("/restaurants/{restaurantId}/assignments/{assignmentId}")
    public AssignmentResource updateAssignment(
            @PathVariable Long assignmentId,
            @PathVariable Long restaurantId,
            @Valid @RequestBody SaveAssignmentResource resource){
        Assignment assignment = convertToEntity(resource);
        return convertToResource(
                assignmentService.updateAssignment(restaurantId,assignmentId,assignment));

    }

    @Operation(summary="Delete Assignment")
    @DeleteMapping("/restaurants/{restaurantId}/assignments/{assignmentId}")
    public ResponseEntity<?> deleteAssignment(
            @PathVariable (value = "restaurantId") Long restaurantId,
            @PathVariable (value = "assignmentId") Long assignmentId) {
        return assignmentService.deleteAssignment(restaurantId, assignmentId);
    }
}
