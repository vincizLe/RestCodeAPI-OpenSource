package com.restcode.restcode.domain.repository;

import com.restcode.restcode.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<Restaurant,Long> {


}
