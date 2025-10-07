package com.foodie.delivery.service;

import java.util.List;

import com.foodie.delivery.dtos.OrderDataRequestDto;
import com.foodie.delivery.dtos.OrderDataResponseDto;

public interface OrdersDataService {
    OrderDataResponseDto createOrder(OrderDataRequestDto dto);
    List<OrderDataResponseDto> getAllOrders();
    OrderDataResponseDto getOrderById(Long id);
    OrderDataResponseDto markPaymentAsPaid(Long orderId);
    OrderDataResponseDto updateOrderStatus(Long orderId, String status);
}
