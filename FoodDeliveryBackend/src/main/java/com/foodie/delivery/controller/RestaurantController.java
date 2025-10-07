package com.foodie.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.delivery.dtos.RestaurantDto;
import com.foodie.delivery.dtos.UserRequestDto;
import com.foodie.delivery.dtos.UserResponseDto;
import com.foodie.delivery.entity.Restaurant;
import com.foodie.delivery.service.RestaurantService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restauranservice;
	
	@GetMapping
	public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
		return ResponseEntity.ok(restauranservice.getAllRestaurants());
	}
	
	@GetMapping("/getrestaurantById/{restaurentId}")
	public ResponseEntity<?> getrestaurantByid(@PathVariable Long restaurentId){
		RestaurantDto rest = restauranservice.getRestaurantById(restaurentId);
		return ResponseEntity.ok(rest);
	}
	
	@PostMapping("/saverestaurant")
	public ResponseEntity<RestaurantDto> postrestaurant(@RequestBody RestaurantDto restaurant){
		RestaurantDto rest = restauranservice.saveRestaurantdata(restaurant);
		return ResponseEntity.ok(rest);
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<RestaurantDto> updateresTaurant(@PathVariable Long restaurentId, @RequestBody RestaurantDto restaurant) {
		RestaurantDto update = restauranservice.updateRestaurant(restaurentId, restaurant);
        return ResponseEntity.ok(update);
    }


	@DeleteMapping("/{restaurentId}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurentId) {
	    try {
	        restauranservice.deleteRestaurant(restaurentId);
	        return ResponseEntity.ok("Restaurant deleted successfully");
	    } catch (EntityNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("Restaurant with id " + restaurentId + " not found");
	    }
	}

}
