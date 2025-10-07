package com.foodie.delivery.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.foodie.delivery.dtos.UserRequestDto;
import com.foodie.delivery.dtos.UserResponseDto;

public interface UserRegistrationService {
	UserResponseDto registerUser(UserRequestDto user);
	//boolean login(String userName, String rawPassword);
	List<UserResponseDto> saveAllusers(List<UserRequestDto> users);
	UserResponseDto getUserById(Long rid);
	List<UserResponseDto> getAllUsers();
	UserResponseDto updateUser(Long rid, UserRequestDto updateUser);
	void deleteUser(Long rid);
	String login(UserRequestDto user);
}
