package com.foodie.delivery.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.foodie.delivery.dtos.UserRequestDto;
import com.foodie.delivery.dtos.UserResponseDto;
import com.foodie.delivery.entity.UserRegistration;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target = "orderData", ignore = true)
	@Mapping(target = "rid", ignore = true)
	UserRegistration convertToentity(UserRequestDto userdto);
	
	@Mapping(source = "rid", target = "rid")
	UserResponseDto convertToDto(UserRegistration userEn);
	
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "orderData", ignore = true)
	@Mapping(target = "rid", ignore = true)
	void updateEntityFromDto(UserRequestDto dto, @MappingTarget UserRegistration entity);
	
}
