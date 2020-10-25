package com.restcode.restcode.domain.service;

import com.restcode.restcode.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestaurantService {
    Page<Restaurant> getAllRestaurants(Pageable pageable);
    Restaurant getRestaurantById(Long restaurantId);
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurantRequest);
    ResponseEntity<?> deleteRestaurant(Long restaurantId);
}
