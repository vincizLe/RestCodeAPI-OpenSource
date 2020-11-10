package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAssignmentRepository extends JpaRepository<Assignment, Long> {
    Page<Assignment> findByConsultantId(Long consultantId, Pageable pageable);
    Page<Assignment> findByRestaurantId(Long restaurantId, Pageable pageable);
}
