package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    Page<Product> getAllProductsByRestaurantId(Long restaurantId, Pageable pageable);
    Product getProductByIdAndRestaurantId(Long restaurantId, Long productId);
    Product createProduct(Long restaurantId, Product product);
    Product updateProduct(Long productId, Product productRequest);
    ResponseEntity<?> deleteProduct(Long restaurantId, Long productId);
}
