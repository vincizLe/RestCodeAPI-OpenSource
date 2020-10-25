package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Category;
import com.restcode.restcode.domain.repository.ICategoryRepository;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import com.restcode.restcode.domain.service.ICategoryService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import com.restcode.restcode.resource.CategoryResource;
import com.restcode.restcode.resource.SaveCategoryResource;
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

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ICategoryService categoryService;

    private CategoryResource convertToResource(Category entity){
        return mapper.map(entity, CategoryResource.class);
    }

    private Category convertToEntity(SaveCategoryResource resource){
        return mapper.map(resource,Category.class);
    }


    @GetMapping("/restaurants/{restaurantId}/categories")
    public Page<CategoryResource> getAllCategoriesByRestaurantId(
            @PathVariable (value = "restaurantId") Long restaurantId, Pageable pageable){
        Page<Category> categoryPage = categoryService.getAllCategoryByRestaurantId(restaurantId,pageable);
        List<CategoryResource> resources = categoryPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }
    @GetMapping("/restaurants/{restaurantId}/categories/{categoryId}")
    public CategoryResource getRestaurantByIdAndCategoryId(
            @PathVariable(name = "restaurantId") Long restaurantId,
            @PathVariable(name = "categoryId") Long categoryId) {
        return convertToResource(categoryService.getCategoryByIdAndRestaurantId(restaurantId, categoryId));
    }

    @PostMapping("/restaurants/{restaurantId}/categories")
    public CategoryResource createCategory( @PathVariable(value = "restaurantId") Long restaurantId,
                                            @Valid @RequestBody SaveCategoryResource resource){
        return convertToResource(categoryService.createCategory(restaurantId,convertToEntity(resource)));
    }


    @DeleteMapping("/restaurants/{restaurantId}/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable (value = "restaurantId") Long restaurantId,
            @PathVariable (value = "categoryId") Long categoryId){
        return categoryService.deleteCategory(restaurantId,categoryId);
    }

}
