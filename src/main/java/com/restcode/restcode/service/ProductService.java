package com.restcode.restcode.service;


import com.restcode.restcode.domain.model.Product;
import com.restcode.restcode.domain.repository.IProductRepository;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import com.restcode.restcode.domain.service.IProductService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
   @Autowired
    private IProductRepository productRepository;

   @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public Page<Product> getAllProductsByRestaurantId(Long restaurantId, Pageable pageable) {
        return productRepository.findByRestaurantId(restaurantId,pageable);
    }

    @Override
    public Product getProductByIdAndRestaurantId(Long restaurantId, Long productId) {
        return productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException("Restaurant","Id",productId));
    }

    @Override
    public Product createProduct(Long restaurantId, Product product) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            product.setRestaurant(restaurant);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Restaurant", "Id", restaurantId));
    }

    @Override
    public Product updateProduct(Long productId, Product productRequest) {
       return null;
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long restaurantId, Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found with Id " + productId));
    }
}
