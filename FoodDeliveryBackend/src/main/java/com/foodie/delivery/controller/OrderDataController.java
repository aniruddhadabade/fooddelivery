package com.foodie.delivery.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.delivery.dtos.OrderDataRequestDto;
import com.foodie.delivery.dtos.OrderDataResponseDto;
import com.foodie.delivery.service.OrdersDataService;

@RestController
@RequestMapping("/api/orders")
public class OrderDataController {
    
	@Autowired
    private OrdersDataService ordersDataService;

    @PostMapping("/createorder")
    public OrderDataResponseDto createOrder(@RequestBody @Valid OrderDataRequestDto requestDto) {
        return ordersDataService.createOrder(requestDto);
    }

    @GetMapping
    public List<OrderDataResponseDto> getAllOrders() {
        return ordersDataService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDataResponseDto getOrderById(@PathVariable Long id) {
        return ordersDataService.getOrderById(id);
    }
    
    @PutMapping("/payment/{orderId}/confirm")
    public OrderDataResponseDto markPaymentAsPaid(@PathVariable Long orderId) {
        return ordersDataService.markPaymentAsPaid(orderId);
    }
    
    @PutMapping("/status/{orderId}")
    public OrderDataResponseDto updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {
        return ordersDataService.updateOrderStatus(orderId, status);
    }
}
