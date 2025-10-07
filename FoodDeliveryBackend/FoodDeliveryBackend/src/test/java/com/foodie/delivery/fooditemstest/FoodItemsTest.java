package com.foodie.delivery.fooditemstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodie.delivery.config.FoodItemMapper;
import com.foodie.delivery.dtos.FoodItemsDto;
import com.foodie.delivery.entity.FoodItem;
import com.foodie.delivery.entity.Restaurant;
import com.foodie.delivery.repository.FoodItemRepo;
import com.foodie.delivery.repository.RestaurantRepo;
import com.foodie.delivery.service.implementation.FoodItemServiceImpl;

@SpringBootTest
public class FoodItemsTest {
    
	@Mock
    private FoodItemRepo foodrepo;

    @Mock
    private RestaurantRepo restaurantrepo;

    @Mock
    private FoodItemMapper mapper;

    @InjectMocks
    private FoodItemServiceImpl service;

    private FoodItem foodItem;
    private FoodItemsDto foodDto;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        restaurant = new Restaurant();
        restaurant.setRestaurentId(1L);

        foodItem = new FoodItem();
        foodItem.setFood_id(1L);
        foodItem.setFood_name("Pizza");
        foodItem.setPrice(200.0);
        foodItem.setCategory("FastFood");
        foodItem.setRestaurant(restaurant);

        foodDto = new FoodItemsDto();
        foodDto.setFood_id(1L);
        foodDto.setFood_name("Pizza");
        foodDto.setPrice(200.0);
        foodDto.setCategory("FastFood");
    }

    @Test
    void testSaveFoodItems() {
        when(restaurantrepo.findById(1L)).thenReturn(Optional.of(restaurant));
        when(mapper.convertToEntity(foodDto)).thenReturn(foodItem);
        when(foodrepo.save(foodItem)).thenReturn(foodItem);
        when(mapper.convertToDto(foodItem)).thenReturn(foodDto);

        FoodItemsDto result = service.saveFoodItems(1L, foodDto);

        assertNotNull(result);
        assertEquals("Pizza", result.getFood_name());

        verify(restaurantrepo, times(1)).findById(1L);
        verify(foodrepo, times(1)).save(foodItem);
    }

    @Test
    void testGetFoodById() {
        when(foodrepo.findById(1L)).thenReturn(Optional.of(foodItem));
        when(mapper.convertToDto(foodItem)).thenReturn(foodDto);

        FoodItemsDto result = service.getFoodById(1L);

        assertNotNull(result);
        assertEquals(200.0, result.getPrice(), 0.001);
        verify(foodrepo, times(1)).findById(1L);
    }

    @Test
    void testUpdateFoodItems() {
        FoodItemsDto updatedDto = new FoodItemsDto();
        updatedDto.setFood_name("Burger");
        updatedDto.setPrice(150.0);
        updatedDto.setCategory("FastFood");

        when(foodrepo.findById(1L)).thenReturn(Optional.of(foodItem));
        doNothing().when(mapper).updateEntityFromDto(updatedDto, foodItem);
        when(foodrepo.save(foodItem)).thenReturn(foodItem);
        when(mapper.convertToDto(foodItem)).thenReturn(updatedDto);

        FoodItemsDto result = service.updateFoodItems(1L, updatedDto);

        assertEquals("Burger", result.getFood_name());
        assertEquals(150.0, result.getPrice(), 0.001);
        verify(foodrepo, times(1)).findById(1L);
        verify(foodrepo, times(1)).save(foodItem);
        verify(mapper, times(1)).updateEntityFromDto(updatedDto, foodItem);
    }

    @Test
    void testDeleteFoodItem() {
        when(foodrepo.existsById(1L)).thenReturn(true);
        doNothing().when(foodrepo).deleteById(1L);

        assertDoesNotThrow(() -> service.deleteFoodItem(1L));

        verify(foodrepo, times(1)).existsById(1L);
        verify(foodrepo, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteFoodItem_NotFound() {
        when(foodrepo.existsById(2L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.deleteFoodItem(2L));
        assertEquals("Food item not found", exception.getMessage());
        verify(foodrepo, times(1)).existsById(2L);
        verify(foodrepo, never()).deleteById(anyLong());
    }
}
