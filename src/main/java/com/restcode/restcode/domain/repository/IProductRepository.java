package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByRestaurantId(Long restaurantId, Pageable pageable);
    Optional<Product> findByIdAndRestaurantId(Long id, Long restaurantId);
}
