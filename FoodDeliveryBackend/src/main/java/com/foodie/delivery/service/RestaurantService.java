package com.foodie.delivery.service;

import java.util.List;

import com.foodie.delivery.dtos.RestaurantDto;

public interface RestaurantService {
	RestaurantDto saveRestaurantdata(RestaurantDto restaurant);
	List<RestaurantDto> saveAllrestaurants(List<RestaurantDto> restaurant);
	RestaurantDto getRestaurantById(Long restaurentId);
	List<RestaurantDto> getAllRestaurants();
	RestaurantDto updateRestaurant(Long restaurentId, RestaurantDto updateRestaurant);
	void deleteRestaurant(Long restaurentId);
}
