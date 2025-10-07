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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.delivery.dtos.UserRequestDto;
import com.foodie.delivery.dtos.UserResponseDto;
import com.foodie.delivery.repository.UserRegistrationRepo;
import com.foodie.delivery.service.UserRegistrationService;

@RestController
@RequestMapping("/api/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationService userservice;
	
	@GetMapping("/getAlluser")
	public ResponseEntity<List<UserResponseDto>> getAllusers(){
		return ResponseEntity.ok(userservice.getAllUsers());
	}
	
	@GetMapping("/getuser/{rid}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable Long rid){
		UserResponseDto userdto = userservice.getUserById(rid);
		return ResponseEntity.ok(userdto);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userDto){
		UserResponseDto cretedto = userservice.registerUser(userDto);
		return new ResponseEntity<>(cretedto, HttpStatus.CREATED);
	}
	
//	@PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) {
//        boolean success = userservice.login(userName, password);
//        if (success) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody UserRequestDto user) {
//	    String result = userservice.login(user);
//	
//	    if (result.equals("Login successful")) {
//	        return ResponseEntity.ok(result);
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
//	    }
//	}
	@PostMapping("/login")
	public String login(@RequestBody UserRequestDto user) {

        return userservice.login(user);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto) {
        UserResponseDto updated = userservice.updateUser(id, userDto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    	userservice.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
