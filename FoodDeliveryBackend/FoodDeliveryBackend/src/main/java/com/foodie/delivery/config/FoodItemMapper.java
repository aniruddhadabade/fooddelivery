package com.foodie.delivery.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.foodie.delivery.dtos.FoodItemsDto;
import com.foodie.delivery.entity.FoodItem;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {
	
	@Mapping(source = "food_id", target = "food_id", ignore = true)
	@Mapping(target = "restaurant", ignore = true)
	@Mapping(target = "orderItems", ignore = true)
	FoodItem convertToEntity(FoodItemsDto dto);
	
	@Mapping(source = "food_id", target = "food_id")
	FoodItemsDto convertToDto(FoodItem items);
	
	@Mapping(source = "food_id", target = "food_id", ignore = true)
	@Mapping(target = "restaurant", ignore = true)
	@Mapping(target = "orderItems", ignore = true)
	void updateEntityFromDto(FoodItemsDto dto, @MappingTarget FoodItem entity);
}
