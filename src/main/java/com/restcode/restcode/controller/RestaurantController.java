package com.restcode.restcode.controller;

import com.restcode.restcode.domain.model.Restaurant;
import com.restcode.restcode.domain.repository.IRestaurantRepository;
import com.restcode.restcode.domain.service.IRestaurantService;
import com.restcode.restcode.exception.ResourceNotFoundException;
import com.restcode.restcode.resource.RestaurantResource;
import com.restcode.restcode.resource.SaveRestaurantResource;
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

@Tag(name="Restaurants", description ="Restaurants API")
@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IRestaurantService restaurantService;

    private RestaurantResource convertToResource(Restaurant entity){
        return mapper.map(entity, RestaurantResource.class);
    }
    private Restaurant convertToEntity(SaveRestaurantResource resource){
        return mapper.map(resource, Restaurant.class);
    }

    @Operation(summary="Get All Restaurants")
    @GetMapping("/restaurants")
    public Page<RestaurantResource> getAllRestaurants(Pageable pageable){
        Page<Restaurant> restaurantPage = restaurantService.getAllRestaurants(pageable);
        List<RestaurantResource> resources = restaurantPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get Restaurant By Id")
    @GetMapping("/restaurants/{restaurantId}")
    public RestaurantResource getRestaurantById(@PathVariable(value = "restaurantId") Long restaurantId){
        return convertToResource(restaurantService.getRestaurantById(restaurantId));
    }

    @Operation(summary="Create Restaurant")
    @PostMapping("/restaurants")
    public RestaurantResource createRestaurant(@Valid @RequestBody SaveRestaurantResource resource){
        Restaurant restaurant = convertToEntity(resource);
        return convertToResource(restaurantService.createRestaurant(restaurant));
    }

    @Operation(summary="Delete Restaurant")
    @PutMapping("/restaurants/{restaurantId}")
    public RestaurantResource updateRestaurant(@PathVariable Long restaurantId, @Valid @RequestBody SaveRestaurantResource resource){
        Restaurant restaurant = convertToEntity(resource);
        return convertToResource(
                restaurantService.updateRestaurant(restaurantId,restaurant));

    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long restaurantId){
        return restaurantService.deleteRestaurant(restaurantId);
    }


   /* @PutMapping("/restaurants/{restaurantId}")
    public Restaurant updatedRestaurant(@PathVariable Long restaurantId, @Valid @RequestBody Restaurant restaurantRequest){
        return IRestaurantRepository.findById(restaurantId).map(restaurant -> {
            restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
            restaurant.setAddress(restaurantRequest.getAddress());
            restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
            return IRestaurantRepository.save(restaurant);
        }).orElseThrow(() -> new ResourceNotFoundException("RestaurantId" + restaurantId + "not found"));
    }*/


}
