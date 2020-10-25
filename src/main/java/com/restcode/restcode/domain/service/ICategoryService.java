package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    Page<Category> getAllCategoryByRestaurantId(Long restaurantId, Pageable pageable);
    Category getCategoryByIdAndRestaurantId(Long restaurantId, Long categoryId);
    Category createCategory(Long restaurantId, Category category);
    Category updateCategory(Long categoryId, Category categoryRequest);
    ResponseEntity<?> deleteCategory(Long restaurantId, Long categoryId);
}
