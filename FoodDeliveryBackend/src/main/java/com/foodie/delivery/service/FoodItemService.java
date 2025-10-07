package com.foodie.delivery.service;

import java.util.List;

import com.foodie.delivery.dtos.FoodItemsDto;

public interface FoodItemService {
	FoodItemsDto saveFoodItems(Long restaurentId, FoodItemsDto dto);
	List<FoodItemsDto> saveAllFoditems(Long restaurentId,List<FoodItemsDto> fooditems);
	List<FoodItemsDto> getFoodItemsbyRestaurentId(Long restaurentId);
	FoodItemsDto getFoodById(Long food_id);
	List<FoodItemsDto> getAllFoodItems();
	FoodItemsDto updateFoodItems(Long food_id, FoodItemsDto dto);
	void deleteFoodItem(Long food_id);
}
