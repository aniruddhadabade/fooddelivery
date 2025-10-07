package com.foodie.delivery.usertests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodie.delivery.dtos.UserRequestDto;
import com.foodie.delivery.dtos.UserResponseDto;
import com.foodie.delivery.entity.UserRegistration;
import com.foodie.delivery.repository.UserRegistrationRepo;
import com.foodie.delivery.service.implementation.UserRegistrationServiceImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	
	@Autowired
	private UserRegistrationServiceImpl userservice;
	
	@Autowired
	private UserRegistrationRepo userRepository;
	
    private UserRequestDto user1;
    private UserRequestDto user2;
	
	@Test
	void contextLoads() {
	}
	
	@BeforeEach
	public void setUsers() {

		user1 = new UserRequestDto(); 
		user1.setName("Amol"); 
		user1.setEmail("amol@gmail.com"); 
		user1.setUserName("amol34"); 
		user1.setPassword("987654"); 
		user1.setAddress("Pawai, Mumbai"); 
		user1.setPhoneNumber("8678676554");

		user2 = new UserRequestDto();
		user2.setName("Sneha");
		user2.setEmail("sneha@gmail.com");
		user2.setUserName("sneha34");
		user2.setPassword("123789");
		user2.setAddress("Vashi, Navi Mumbai");
		user2.setPhoneNumber("9988776655");

	}
	
	@Test
	@Disabled
	public void testSaveAllUsers() {
	    List<UserRequestDto> usersToSave = List.of(user1, user2);

	    List<UserResponseDto> savedUsers = userservice.saveAllusers(usersToSave);

	    assertNotNull(savedUsers);                  
	    assertEquals(2, savedUsers.size());          

	    assertEquals(user1.getUserName(), savedUsers.get(0).getUserName());
	    assertEquals(user2.getUserName(), savedUsers.get(1).getUserName());

	    savedUsers.forEach(System.out::println);
	}
	
	@Test
	@Disabled
	public void createUser() {
		UserResponseDto saved = userservice.registerUser(user1);
		 assertNotNull(saved);
		 assertEquals("amol34", saved.getUserName());
	}

	@Test
	public void getUser() {
	    Long rid = 4L;

	    UserResponseDto fetched = userservice.getUserById(rid);

	    assertNotNull(fetched);  
	    assertEquals(rid, fetched.getRid()); 
	    assertEquals("ani34", fetched.getUserName()); 
	}
	
	@Test
	@Disabled
	public void getAlluser() {
		List<UserResponseDto> fetch = userservice.getAllUsers();
		
		for(UserResponseDto user:fetch) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testLoginSuccess() {
	    String token = userservice.login(new UserRequestDto() {{
	        setUserName("ujjwal34");
	        setPassword("124567");
	    }});

	    assertNotNull(token);        
	    assertNotEquals("Login failed", token);

	    System.out.println("JWT Token: " 
	    + token);
	}
	
	@Test
	public void testgetUserById() {
		Long rid = 11L;
		
		UserResponseDto res = userservice.getUserById(rid);
		assertNotNull(res);
		assertEquals("amol34", res.getUserName());
		assertEquals("amol@gmail.com", res.getEmail());
	}
	
	@Test
	public void testGetUserById_NotFound() {
	    Long rid = 12L;

	    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
	        userservice.getUserById(rid);
	    });

	    assertEquals("User not found with id: 12", exception.getMessage());
	}

	
	@Test
	public void testUpdateUser() {
		UserRegistration sneha = userRepository.findAll().stream()
	            .filter(u -> u.getName().equals("Sneha"))
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    UserRequestDto updateDto = new UserRequestDto();
	    updateDto.setName("Sneha Sharma");            
	    updateDto.setEmail("sneha.sharma@gmail.com"); 
	    updateDto.setUserName("snehaSh34");          
	    updateDto.setPassword("987654321");          
	    updateDto.setAddress("Belapur, Navi Mumbai"); 
	    updateDto.setPhoneNumber("9123456780");       

	    UserResponseDto updatedUser = userservice.updateUser(sneha.getRid(), updateDto);

	    assertNotNull(updatedUser);
	    assertEquals("Sneha Sharma", updatedUser.getName());
	    assertEquals("sneha.sharma@gmail.com", updatedUser.getEmail());
	    assertEquals("snehaSh34", updatedUser.getUserName());
	    assertEquals("Belapur, Navi Mumbai", updatedUser.getAddress());
	    assertEquals("9123456780", updatedUser.getPhoneNumber());
	}
	
	@Test
	void testDeleteUser() {
		Long id = 11L;
		userservice.deleteUser(id);
		 Optional<UserRegistration> deleted = userRepository.findById(11L);
	     assertTrue(deleted.isEmpty(), "User should be deleted");
	}
}
