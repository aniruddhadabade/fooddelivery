package com.foodie.delivery.config;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.foodie.delivery.dtos.OrderItemResponse;
import com.foodie.delivery.dtos.OrderItemsRequestDto;
import com.foodie.delivery.entity.FoodItem;
import com.foodie.delivery.entity.OrderItems;
import com.foodie.delivery.entity.OrdersData;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    default OrderItems toEntity(OrderItemsRequestDto dto, OrdersData orderdata, FoodItem foodItem) {
        if (dto == null) return null;
        OrderItems entity = new OrderItems();
        entity.setOrderdata(orderdata);  
        entity.setFoodItem(foodItem);    
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    default List<OrderItems> toEntityList(List<OrderItemsRequestDto> dtos, OrdersData orderdata, List<FoodItem> foodItems) {
        if (dtos == null || dtos.isEmpty()) return null;
        List<OrderItems> entities = new java.util.ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            entities.add(toEntity(dtos.get(i), orderdata, foodItems.get(i)));
        }
        return entities;
    }

    @Mapping(source = "item_id", target = "itemId")
    @Mapping(source = "orderdata.orderId", target = "orderId")
    @Mapping(source = "foodItem.food_id", target = "foodItemId")
    @Mapping(source = "foodItem.food_name", target = "foodName")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    OrderItemResponse toDto(OrderItems entity);

    List<OrderItemResponse> toDtoList(List<OrderItems> entities);
}
