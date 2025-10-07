package com.foodie.delivery.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.foodie.delivery.dtos.RestaurantDto;
import com.foodie.delivery.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
	@Mapping(target = "restaurentId", ignore = true)
	@Mapping(target = "foodItems", ignore = true)
	@Mapping(target = "orderData", ignore = true)
	Restaurant convertToEntity(RestaurantDto rdto);
	
	@Mapping(source = "restaurentId", target = "restaurentId")
	RestaurantDto convertToDto(Restaurant resEn);
	
	@Mapping(target = "restaurentId", ignore = true)
	@Mapping(target = "foodItems", ignore = true)
	@Mapping(target = "orderData", ignore = true)
	void updateEntityFromDto(RestaurantDto reqDto, @MappingTarget Restaurant ren);
}
