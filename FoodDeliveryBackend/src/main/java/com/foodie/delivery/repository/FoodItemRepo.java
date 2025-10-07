package com.foodie.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.delivery.entity.FoodItem;

public interface FoodItemRepo extends JpaRepository<FoodItem,Long>{
	List<FoodItem> findByRestaurantRestaurentId(Long restaurentId);
	
}