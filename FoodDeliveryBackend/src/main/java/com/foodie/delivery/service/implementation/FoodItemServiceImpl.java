package com.foodie.delivery.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.delivery.config.FoodItemMapper;
import com.foodie.delivery.dtos.FoodItemsDto;
import com.foodie.delivery.entity.FoodItem;
import com.foodie.delivery.entity.Restaurant;
import com.foodie.delivery.repository.FoodItemRepo;
import com.foodie.delivery.repository.RestaurantRepo;
import com.foodie.delivery.service.FoodItemService;

@Service
public class FoodItemServiceImpl implements FoodItemService{
	
	@Autowired
	private FoodItemRepo foodrepo;
	
	@Autowired
	private RestaurantRepo restaurantrepo;
	
	@Autowired
	private FoodItemMapper mapper;
	
	private FoodItem convertToentity(FoodItemsDto dto) {
		FoodItem food = mapper.convertToEntity(dto);
		return food;
	}
	
	private FoodItemsDto convertToDto(FoodItem rest) {
		FoodItemsDto dto = mapper.convertToDto(rest);
		return dto;
	}
	
	@Override
	public FoodItemsDto saveFoodItems(Long restaurentId, FoodItemsDto dto) {
		Restaurant restaurant = restaurantrepo.findById(restaurentId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        FoodItem entity = convertToentity(dto);
        entity.setRestaurant(restaurant);

        FoodItem saved = foodrepo.save(entity);
        return convertToDto(saved);
	}
	
	@Override
	public List<FoodItemsDto> saveAllFoditems(Long restaurentId, List<FoodItemsDto> fooditems) {
	    Restaurant restaurant = restaurantrepo.findById(restaurentId)
	        .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurentId));

	    List<FoodItem> entities = fooditems.stream()
	            .map(this::convertToentity)
	            .peek(entity -> entity.setRestaurant(restaurant))
	            .toList();

	    List<FoodItem> saved = foodrepo.saveAll(entities);

	    return saved.stream()
	            .map(this::convertToDto)
	            .toList();
	}

	
	@Override
    public List<FoodItemsDto> getFoodItemsbyRestaurentId(Long restaurentId) {
        return foodrepo.findByRestaurantRestaurentId(restaurentId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public FoodItemsDto getFoodById(Long food_id) {
        FoodItem entity = foodrepo.findById(food_id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));
        return convertToDto(entity);
    }
    
    @Override
    public List<FoodItemsDto> getAllFoodItems(){
    	return foodrepo.findAll().stream().map(this::convertToDto).toList();
    }
	

    @Override
    public FoodItemsDto updateFoodItems(Long food_id, FoodItemsDto dto) {
        FoodItem entity = foodrepo.findById(food_id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        mapper.updateEntityFromDto(dto, entity);
        FoodItem save = foodrepo.save(entity);
        return convertToDto(save);
    }

    @Override
    public void deleteFoodItem(Long foodItemId) {
        if (!foodrepo.existsById(foodItemId)) {
            throw new RuntimeException("Food item not found");
        }
        foodrepo.deleteById(foodItemId);
    }
}
