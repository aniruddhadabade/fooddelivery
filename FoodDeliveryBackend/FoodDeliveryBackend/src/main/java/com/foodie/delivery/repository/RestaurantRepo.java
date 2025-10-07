package com.foodie.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.delivery.entity.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long>{

}
