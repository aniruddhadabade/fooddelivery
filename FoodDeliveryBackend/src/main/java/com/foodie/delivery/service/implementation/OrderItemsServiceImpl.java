package com.foodie.delivery.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.delivery.config.OrderItemMapper;
import com.foodie.delivery.dtos.OrderItemResponse;
import com.foodie.delivery.dtos.OrderItemsRequestDto;
import com.foodie.delivery.entity.FoodItem;
import com.foodie.delivery.entity.OrderItems;
import com.foodie.delivery.entity.OrdersData;
import com.foodie.delivery.repository.FoodItemRepo;
import com.foodie.delivery.repository.OrderItemsRepo;
import com.foodie.delivery.service.OrderItemsService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderItemsServiceImpl implements OrderItemsService{
	
	@Autowired
    private OrderItemsRepo orderItemsRepository;

    @Autowired
    private FoodItemRepo foodItemRepository;

    @Autowired
    private OrderItemMapper mapper;

    @Override
    public OrderItemResponse createOrderItem(OrderItemsRequestDto dto, OrdersData orderdata) {
        FoodItem foodItem = foodItemRepository.findById(dto.getFoodItemId())
                                .orElseThrow(() -> new RuntimeException("Food item not found"));

        OrderItems entity = mapper.toEntity(dto, orderdata, foodItem);
        OrderItems saved = orderItemsRepository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public List<OrderItemResponse> createOrderItems(List<OrderItemsRequestDto> dtos, OrdersData orderdata) {
        List<OrderItemResponse> responseList = new ArrayList<>();
        for (OrderItemsRequestDto dto : dtos) {
            responseList.add(createOrderItem(dto, orderdata));
        }
        return responseList;
    }

    @Override
    public List<OrderItemResponse> getItemsByOrder(OrdersData orderdata) {
        List<OrderItems> items = orderItemsRepository.findByOrderdata(orderdata);
        return mapper.toDtoList(items);
    }
}
