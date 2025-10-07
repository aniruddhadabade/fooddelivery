package com.foodie.delivery.config;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.foodie.delivery.dtos.OrderDataRequestDto;
import com.foodie.delivery.dtos.OrderDataResponseDto;
import com.foodie.delivery.dtos.OrderItemResponse;
import com.foodie.delivery.dtos.OrderItemsRequestDto;
import com.foodie.delivery.entity.OrderItems;
import com.foodie.delivery.entity.OrdersData;

@Mapper(componentModel = "spring")
public interface OrderDataMapper {
	
    @Mapping(source = "userRegistration.rid", target = "userId")
    @Mapping(source = "restaurant.restaurentId", target = "restaurantId")
    @Mapping(source = "orderItems", target = "items")
    @Mapping(source = "payment.amount", target = "totalAmount")
    @Mapping(source = "payment.status", target = "paymentStatus")
    OrderDataResponseDto toDto(OrdersData order);

    @Mapping(source = "item_id", target = "itemId")
    @Mapping(source = "orderdata.orderId", target = "orderId")
    @Mapping(source = "foodItem.food_id", target = "foodItemId")
    @Mapping(source = "foodItem.food_name", target = "foodName")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    OrderItemResponse orderItemToDto(OrderItems orderItem);

    List<OrderItemResponse> orderItemsToDtoList(List<OrderItems> items);
    
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "userRegistration", ignore = true)
    OrdersData toEntity(OrderDataRequestDto dto);

    @Mapping(source = "foodItemId", target = "foodItem.food_id")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(target = "item_id", ignore = true) 
    @Mapping(target = "orderdata", ignore = true)   
    OrderItems toEntity(OrderItemsRequestDto dto);

    List<OrderItems> orderItemsToEntityList(List<OrderItemsRequestDto> items);
	
}
