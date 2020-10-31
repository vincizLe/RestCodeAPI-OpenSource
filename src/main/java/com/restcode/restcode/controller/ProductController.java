package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.domain.service.IProductService;
import com.restcode.restcode.resource.ProductResource;
import com.restcode.restcode.resource.SaveProductResource;
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

@Tag(name="Products", description ="Products API")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IProductService productService;

    private ProductResource convertToResource(Product entity){
        return mapper.map(entity, ProductResource.class);
    }

    private Product convertToEntity(SaveProductResource resource){
        return mapper.map(resource, Product.class);
    }

    @Operation(summary="Get All Products By Id")
    @GetMapping("/restaurants/{restaurantId}/products")
    public Page<ProductResource> getAllProductsByRestaurantId(
            @PathVariable (value = "restaurantId") Long restaurantId, Pageable pageable){
        Page<Product> productPage = productService.getAllProductByRestaurantId(restaurantId,pageable);
        List<ProductResource> resources = productPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary="Get Product By Id")
    @GetMapping("/restaurants/{restaurantId}/products/{productId}")
    public ProductResource getProductByIdAndRestaurantId(
            @PathVariable(name = "restaurantId") Long restaurantId,
            @PathVariable(name = "productId") Long productId) {
        return convertToResource(productService.getProductByIdAndRestaurantId(restaurantId, productId));
    }

    @Operation(summary="Create Product")
        @PostMapping("/restaurants/{restaurantId}/products")
        public ProductResource createProduct(@PathVariable(value = "restaurantId") Long restaurantId,
                                              @Valid @RequestBody SaveProductResource resource){
            return convertToResource(productService.createProduct(restaurantId,convertToEntity(resource)));
        }

    @Operation(summary="Delete Product")
    @DeleteMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable (value = "restaurantId") Long restaurantId,
            @PathVariable (value = "productId") Long productId){
        return productService.deleteProduct(restaurantId,productId);
    }

}
