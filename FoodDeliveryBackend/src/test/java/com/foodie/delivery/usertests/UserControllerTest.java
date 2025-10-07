package com.foodie.delivery.usertests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodie.delivery.controller.UserRegistrationController;
import com.foodie.delivery.dtos.UserRequestDto;
import com.foodie.delivery.dtos.UserResponseDto;
import com.foodie.delivery.service.UserRegistrationService;

@SpringBootTest
public class UserControllerTest {
	private MockMvc mockMvc;

    @Mock
    private UserRegistrationService userService;

    @InjectMocks
    private UserRegistrationController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private UserRequestDto requestDto;
    private UserResponseDto responseDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        requestDto = new UserRequestDto();
        requestDto.setUserName("testuser");
        requestDto.setPassword("password123");

        responseDto = new UserResponseDto();
        responseDto.setRid(1L);
        responseDto.setUserName("testuser");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserResponseDto> users = Arrays.asList(responseDto);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/registration/getAlluser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].userName").value("testuser"));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/registration/getuser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.userName").value("testuser"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        when(userService.registerUser(any(UserRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/registration/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.userName").value("testuser"));
    }

    @Test
    public void testLogin() throws Exception {
        when(userService.login(any(UserRequestDto.class))).thenReturn("Login successful");

        mockMvc.perform(post("/api/registration/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.updateUser(eq(1L), any(UserRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/registration/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.userName").value("testuser"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/registration/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }
}
