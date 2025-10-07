package com.foodie.delivery.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.delivery.config.RestaurantMapper;
import com.foodie.delivery.dtos.RestaurantDto;
import com.foodie.delivery.entity.Restaurant;
import com.foodie.delivery.repository.RestaurantRepo;
import com.foodie.delivery.service.RestaurantService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepo restaurantrepo;
	
	@Autowired
	private RestaurantMapper restaurantMapper;
	
	private Restaurant convertToentity(RestaurantDto dto) {
		Restaurant rest = restaurantMapper.convertToEntity(dto);
		return rest;
	}
	
	private RestaurantDto convertToDto(Restaurant rest) {
		RestaurantDto dto = restaurantMapper.convertToDto(rest);
		return dto;
	}
	
	@Override
	public RestaurantDto saveRestaurantdata(RestaurantDto restaurant) {
		Restaurant rest = convertToentity(restaurant);
		Restaurant saveRestaurant = restaurantrepo.save(rest);
		return convertToDto(saveRestaurant);
	}
	
	@Override
	public List<RestaurantDto> saveAllrestaurants(List<RestaurantDto> restaurant){
		List<Restaurant> entities = restaurant.stream().map(this::convertToentity).toList();
		List<Restaurant> saveAllrestaurant = restaurantrepo.saveAll(entities);
		return saveAllrestaurant.stream().map(this::convertToDto).toList();
	}
	
	@Override
	public RestaurantDto getRestaurantById(Long restaurentId) {
		Restaurant rest = restaurantrepo.findById(restaurentId)
				.orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurentId));;
		return convertToDto(rest);
	}
	
	@Override
	public List<RestaurantDto> getAllRestaurants(){
		return restaurantrepo.findAll().stream().map(this::convertToDto).toList();
	}
	
	@Override
	public RestaurantDto updateRestaurant(Long restaurentId, RestaurantDto updateRestaurant) {
		Restaurant existingRestaurant = restaurantrepo.findById(restaurentId).orElseThrow(() -> new RuntimeException("User not found with " + restaurentId));
//		existingRestaurant.setRname(updateRestaurant.getRname());
//		existingRestaurant.setRaddress(updateRestaurant.getRaddress());
//		existingRestaurant.setEmail(updateRestaurant.getEmail());
//		existingRestaurant.setContact_number(updateRestaurant.getContact_number());
//		existingRestaurant.setCity(updateRestaurant.getCity());
//		existingRestaurant.setState(updateRestaurant.getState());
//		existingRestaurant.setCountry(updateRestaurant.getCountry());
		restaurantMapper.updateEntityFromDto(updateRestaurant, existingRestaurant);
		Restaurant save = restaurantrepo.save(existingRestaurant);
		return restaurantMapper.convertToDto(save);
		
	}
	
	@Override
	public void deleteRestaurant(Long restaurentId) {
		Restaurant restaurant = restaurantrepo.findById(restaurentId)
		        .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + restaurentId));
		restaurantrepo.delete(restaurant);
	}
}
