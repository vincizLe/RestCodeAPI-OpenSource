package com.restcode.restcode.service;


import com.restcode.restcode.domain.model.Category;
import com.restcode.restcode.domain.repository.ICategoryRepository;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import com.restcode.restcode.domain.service.ICategoryService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
   @Autowired
    private ICategoryRepository categoryRepository;

   @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public Page<Category> getAllCategoryByRestaurantId(Long restaurantId, Pageable pageable) {
        return categoryRepository.findByRestaurantId(restaurantId,pageable);
    }

    @Override
    public Category getCategoryByIdAndRestaurantId(Long restaurantId, Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Restaurant","Id",categoryId));
    }

    @Override
    public Category createCategory(Long restaurantId, Category category) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            category.setRestaurant(restaurant);
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Restaurant", "Id", restaurantId));
    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryRequest) {
       return null;
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long restaurantId, Long categoryId) {
        return categoryRepository.findById(categoryId).map(category -> {
            categoryRepository.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found with Id " + categoryId));
    }
}
