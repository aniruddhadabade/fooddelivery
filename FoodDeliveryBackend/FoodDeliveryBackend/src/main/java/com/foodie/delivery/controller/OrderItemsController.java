package com.foodie.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.delivery.dtos.OrderItemResponse;
import com.foodie.delivery.dtos.OrderItemsRequestDto;
import com.foodie.delivery.entity.OrdersData;
import com.foodie.delivery.repository.OrdersDataRepo;
import com.foodie.delivery.service.OrderItemsService;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemsController {
	@Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private OrdersDataRepo ordersDataRepository;
    
    @PostMapping("/create/{orderId}")
    public OrderItemResponse createOrderItem(@PathVariable Long orderId, @RequestBody OrderItemsRequestDto dto) {
        OrdersData order = ordersDataRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderItemsService.createOrderItem(dto, order);
    }

    @PostMapping("/create-multiple/{orderId}")
    public List<OrderItemResponse> createOrderItems(@PathVariable Long orderId, @RequestBody List<OrderItemsRequestDto> dtos) {

        OrdersData order = ordersDataRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderItemsService.createOrderItems(dtos, order);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItemResponse> getItemsByOrder(@PathVariable Long orderId) {

        OrdersData order = ordersDataRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderItemsService.getItemsByOrder(order);
    }
}
