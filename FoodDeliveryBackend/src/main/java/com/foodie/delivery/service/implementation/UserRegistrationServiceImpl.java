package com.foodie.delivery.service.implementation;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodie.delivery.config.UserMapper;
import com.foodie.delivery.dtos.UserRequestDto;
import com.foodie.delivery.dtos.UserResponseDto;
import com.foodie.delivery.entity.UserPrincipal;
import com.foodie.delivery.entity.UserRegistration;
import com.foodie.delivery.repository.UserRegistrationRepo;
import com.foodie.delivery.service.UserRegistrationService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService,UserDetailsService{
	
	@Autowired
	private UserRegistrationRepo userrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	@Lazy
	AuthenticationManager authManager; 
	
	@Autowired
	private JwtServiceImpl jwtservice;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
	
	@Autowired
	private UserMapper usermapper = Mappers.getMapper(UserMapper.class);
	
	private UserRegistration convertToEntity(UserRequestDto dto) {
//		UserRegistration user = new UserRegistration();
//		UserRegistration user = this.modelMapper.map(dto, UserRegistration.class);
		UserRegistration user = usermapper.convertToentity(dto);
		
//		user.setUserName(dto.getUserName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setAddress(dto.getAddress());
//		user.setPhoneNumber(dto.getPhoneNumber());
//		user.setName(dto.getName());
		return user;
	}
	
	private UserResponseDto convertToDto(UserRegistration userEn) {
//		UserResponseDto dto = new UserResponseDto();
//		UserResponseDto dto = this.modelMapper.map(userEn, UserResponseDto.class);
//		dto.setRid(userEn.getRid());
//		dto.setUserName(userEn.getUserName());
//		dto.setEmail(userEn.getEmail());
//		dto.setName(userEn.getName());
//		dto.setPhoneNumber(userEn.getPhoneNumber());
//		dto.setAddress(userEn.getAddress());
		UserResponseDto dto = usermapper.convertToDto(userEn);
		return dto;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    UserRegistration user = userrepo.findByUserName(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		return new UserPrincipal(user);
	}
	
	@Override
	public UserResponseDto registerUser(UserRequestDto user) {
		if (userrepo.findByUserName(user.getUserName()).isPresent()) {
		    throw new RuntimeException("Username already exists!");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		UserRegistration entity = convertToEntity(user);
		UserRegistration saveuser = userrepo.save(entity);
		return convertToDto(saveuser);
	}
	
//	@Override
//	public boolean login(String userName, String rawPassword) {
//		UserRegistration user = userrepo.findByUserName(userName)
//				.orElseThrow(() -> new RuntimeException("User not found"));
//        return encoder.matches(rawPassword, user.getPassword());
//	}
	
	@Override
	public String login(UserRequestDto user) {
		Authentication authentication = authManager.authenticate( 
				new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
				
		if(authentication.isAuthenticated()) {
			return jwtservice.generateToken(user.getUserName());
		}
		else {
			 return "Login failed";
		}
	   
	}

	
	@Override
	public List<UserResponseDto> saveAllusers(List<UserRequestDto> users) {
	    List<UserRegistration> entities = users.stream()
	            .map(this::convertToEntity)
	            .toList();
	
	    List<UserRegistration> saved = userrepo.saveAll(entities);
	
	    return saved.stream()
	            .map(this::convertToDto)
	            .toList();
	}
	
	@Override
	public UserResponseDto getUserById(Long rid) {
	    UserRegistration user = userrepo.findById(rid)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + rid));
	    return convertToDto(user);
	}
	
	@Override
	public List<UserResponseDto> getAllUsers() {
	    return userrepo.findAll().stream()
	            .map(this::convertToDto)
	            .toList();
	}
	
	@Override
	public UserResponseDto updateUser(Long rid, UserRequestDto updateUser) {
	    UserRegistration existing = userrepo.findById(rid)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + rid));
	
	//	        existing.setUserName(updateUser.getUserName());
	//	        existing.setName(updateUser.getName());
	//	        existing.setEmail(updateUser.getEmail());
	//	        existing.setPassword(updateUser.getPassword());
	//	        existing.setAddress(updateUser.getAddress());
	//	        existing.setPhoneNumber(updateUser.getPhoneNumber());
	//
	usermapper.updateEntityFromDto(updateUser, existing);
	UserRegistration saved = userrepo.save(existing);
	return usermapper.convertToDto(saved);
	//	        return convertToDto(saved);
	}
	
	@Override
	public void deleteUser(Long rid) {
		UserRegistration user = userrepo.findById(rid).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + rid));
	    userrepo.delete(user);
	}
}
