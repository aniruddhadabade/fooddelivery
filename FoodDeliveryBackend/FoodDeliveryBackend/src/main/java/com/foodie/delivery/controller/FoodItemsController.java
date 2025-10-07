package com.foodie.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.delivery.dtos.FoodItemsDto;
import com.foodie.delivery.service.FoodItemService;

@RestController
@RequestMapping("/api/fooditems")
public class FoodItemsController {
	
	@Autowired
	private FoodItemService foodservice;
	
	@GetMapping
	public ResponseEntity<List<FoodItemsDto>> getAllFooditesm() {
	    return ResponseEntity.ok(foodservice.getAllFoodItems());
	}
	
	@GetMapping("/getByid/{food_id}")
	public ResponseEntity<FoodItemsDto> getById(@PathVariable Long food_id) {
	    FoodItemsDto food = foodservice.getFoodById(food_id);
	    return ResponseEntity.ok(food);
	}
	
	@GetMapping("/getfood/{restaurantId}")
	public ResponseEntity<List<FoodItemsDto>> getFoodItems(@PathVariable Long restaurantId) {
	    return ResponseEntity.ok(foodservice.getFoodItemsbyRestaurentId(restaurantId));
	}
	
	@PostMapping("/save/{restaurantId}")
	public ResponseEntity<FoodItemsDto> addFoodItem(
	        @PathVariable Long restaurantId,
	        @RequestBody FoodItemsDto dto) {
	    return ResponseEntity.ok(foodservice.saveFoodItems(restaurantId, dto));
	}
	
	@PostMapping("/bulk/{restaurantId}")
	public ResponseEntity<List<FoodItemsDto>> addFoodItems(
	        @PathVariable Long restaurantId,
	        @RequestBody List<FoodItemsDto> dtos) {
	    return ResponseEntity.ok(foodservice.saveAllFoditems(restaurantId, dtos));
	}
	
	@PutMapping("/{foodId}")
	public ResponseEntity<FoodItemsDto> updateFoodItem(
	        @PathVariable Long foodId,
	        @RequestBody FoodItemsDto dto) {
	    return ResponseEntity.ok(foodservice.updateFoodItems(foodId, dto));
	}
	
	@DeleteMapping("/{foodId}")
	public ResponseEntity<String> deleteFoodItem(@PathVariable Long foodId) {
	    foodservice.deleteFoodItem(foodId);
	    return ResponseEntity.ok("Food item deleted successfully");
	}
	
	
}
