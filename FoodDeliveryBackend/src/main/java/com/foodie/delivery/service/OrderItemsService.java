package com.foodie.delivery.service;

import java.util.List;

import com.foodie.delivery.dtos.OrderItemResponse;
import com.foodie.delivery.dtos.OrderItemsRequestDto;
import com.foodie.delivery.entity.OrdersData;

public interface OrderItemsService {
    OrderItemResponse createOrderItem(OrderItemsRequestDto dto, OrdersData orderdata);

    List<OrderItemResponse> createOrderItems(List<OrderItemsRequestDto> dtos, OrdersData orderdata);

    List<OrderItemResponse> getItemsByOrder(OrdersData orderdata);
}
