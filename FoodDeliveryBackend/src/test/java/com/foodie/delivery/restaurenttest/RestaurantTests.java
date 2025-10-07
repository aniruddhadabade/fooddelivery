package com.foodie.delivery.restaurenttest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodie.delivery.config.RestaurantMapper;
import com.foodie.delivery.dtos.RestaurantDto;
import com.foodie.delivery.entity.Restaurant;
import com.foodie.delivery.repository.RestaurantRepo;
import com.foodie.delivery.service.implementation.RestaurantServiceImpl;

@SpringBootTest
public class RestaurantTests {
	
	@Mock
	private RestaurantRepo repo;
	
	@Mock
	private RestaurantMapper restaurantMapper;
	
	@InjectMocks
    private RestaurantServiceImpl restaurantService;
	
	private Restaurant restaurant;
    private RestaurantDto restaurantDto;
    
    @BeforeEach
    public void setup() {
    	restaurant = new Restaurant();
    	restaurant.setRestaurentId(1L);
    	restaurant.setRname("China Hunt");
    	restaurant.setEmail("chinahunt@gmail.com");
    	restaurant.setRaddress("Goregaon,Mumbai");
    	restaurant.setCity("Mumbai");
    	restaurant.setState("Maharashtra");
    	restaurant.setCountry("India");
    	restaurant.setContact_number("8291676756");
    	
    	restaurantDto = new RestaurantDto();
    	restaurantDto.setRestaurentId(1L);
    	restaurantDto.setRname("China Hunt");
    	restaurantDto.setEmail("chinahunt@gmail.com");
    	restaurantDto.setRaddress("Goregaon,Mumbai");
    	restaurantDto.setCity("Mumbai");
    	restaurantDto.setState("Maharashtra");
    	restaurantDto.setCountry("India");
    	restaurantDto.setContact_number("8291676756");
    }
    
    @Test
   // @Disabled
    public void saveRestarantData() {
        when(restaurantMapper.convertToEntity(restaurantDto)).thenReturn(restaurant);
        
        when(repo.save(restaurant)).thenReturn(restaurant);
        
        when(restaurantMapper.convertToDto(restaurant)).thenReturn(restaurantDto);
        
        RestaurantDto result = restaurantService.saveRestaurantdata(restaurantDto);
        
        assertNotNull(result);
        assertEquals("China Hunt", result.getRname());
        assertEquals("chinahunt@gmail.com", result.getEmail());
        
        verify(restaurantMapper).convertToEntity(restaurantDto);
        verify(repo).save(restaurant);
        verify(restaurantMapper).convertToDto(restaurant);
    }
    
    @Test
    public void getRestaurant() {
    	when(repo.findById(1L)).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.convertToDto(restaurant)).thenReturn(restaurantDto);

        RestaurantDto result = restaurantService.getRestaurantById(1L);
        
        System.out.println(result);

        assertNotNull(result);
        assertEquals("China Hunt", result.getRname());
        verify(repo, times(1)).findById(1L);
    }
    
    @Test
    public void testGetAllRestaurants() {
        when(repo.findAll()).thenReturn(Arrays.asList(restaurant));
        when(restaurantMapper.convertToDto(restaurant)).thenReturn(restaurantDto);

        List<RestaurantDto> result = restaurantService.getAllRestaurants();

        assertEquals(1, result.size());
        assertEquals("China Hunt", result.get(0).getRname());
        verify(repo, times(1)).findAll();
    }
    
    @Test
    public void deleteRestaurant() {
	    when(repo.findById(1L)).thenReturn(Optional.of(restaurant));
	    doNothing().when(repo).delete(restaurant);
	    restaurantService.deleteRestaurant(1L);
	    verify(repo, times(1)).findById(1L);
	    verify(repo, times(1)).delete(restaurant);
    }
}
