package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByRestaurantId(Long restaurantId, Pageable pageable);
    Optional<Category> findByIdAndRestaurantId(Long id, Long restaurantId);
}
