package com.restcode.restcode.service;

import com.restcode.restcode.domain.model.Restaurant;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import com.restcode.restcode.domain.service.IRestaurantService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant", "Id",restaurantId));
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant","Id",restaurantId));
        return restaurantRepository.save( restaurant.setName(restaurantRequest.getName())
        .setAddress(restaurantRequest.getAddress())
        .setPhoneNumber(restaurantRequest.getPhoneNumber()));
    }

    @Override
    public ResponseEntity<?> deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new ResourceNotFoundException("Sale","Id",restaurantId));
        restaurantRepository.delete(restaurant);
        return ResponseEntity.ok().build();
    }
}
