package com.foodie.delivery.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.delivery.config.OrderDataMapper;
import com.foodie.delivery.config.OrderStatus;
import com.foodie.delivery.config.PaymentStatus;
import com.foodie.delivery.dtos.OrderDataRequestDto;
import com.foodie.delivery.dtos.OrderDataResponseDto;
import com.foodie.delivery.entity.FoodItem;
import com.foodie.delivery.entity.OrderItems;
import com.foodie.delivery.entity.OrdersData;
import com.foodie.delivery.entity.Payment;
import com.foodie.delivery.entity.Restaurant;
import com.foodie.delivery.entity.UserRegistration;
import com.foodie.delivery.repository.FoodItemRepo;
import com.foodie.delivery.repository.OrdersDataRepo;
import com.foodie.delivery.repository.RestaurantRepo;
import com.foodie.delivery.repository.UserRegistrationRepo;
import com.foodie.delivery.service.OrdersDataService;

@Service
public class OrdersDataServiceImpl implements OrdersDataService{
    
	@Autowired
    private OrdersDataRepo ordersDataRepository;

    @Autowired
    private UserRegistrationRepo userRegistrationRepository;

    @Autowired
    private RestaurantRepo restaurantRepository;

    @Autowired
    private OrderDataMapper orderDataMapper;
    
    @Autowired
    private FoodItemRepo foodItemRepo;


    @Override
    public OrderDataResponseDto createOrder(OrderDataRequestDto dto) {
        OrdersData order = new OrdersData();
        order.setStatus(dto.getStatus()); // PENDING
        order.setDate(dto.getDate());

        // Fetch User and Restaurant
        UserRegistration user = userRegistrationRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        order.setUserRegistration(user);
        order.setRestaurant(restaurant);

        // Map order items
        List<OrderItems> items = dto.getItems().stream()
                .map(itemDto -> {
                    OrderItems item = new OrderItems();
                    FoodItem food = foodItemRepo.findById(itemDto.getFoodItemId())
                            .orElseThrow(() -> new RuntimeException("Food item not found"));
                    item.setFoodItem(food);
                    item.setQuantity(itemDto.getQuantity());
                    item.setPrice(itemDto.getPrice());
                    item.setOrderdata(order); // set parent order
                    return item;
                })
                .collect(Collectors.toList());
        order.setOrderItems(items);

        // Calculate total including GST
        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        double totalWithGst = total + (total * 0.18); // 18% GST

    
        Payment payment = new Payment();
        payment.setOrderdata(order);
        payment.setAmount(totalWithGst);
        payment.setPayment_method(dto.getPaymentMethod());
        payment.setStatus(PaymentStatus.PENDING); 
        order.setPayment(payment);


        OrdersData savedOrder = ordersDataRepository.save(order);

        return orderDataMapper.toDto(savedOrder);
    }


    @Override
    public List<OrderDataResponseDto> getAllOrders() {
        return ordersDataRepository.findAll()
                .stream()
                .map(orderDataMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDataResponseDto getOrderById(Long id) {
        OrdersData order = ordersDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderDataMapper.toDto(order);
    }
    
    @Override
    public OrderDataResponseDto markPaymentAsPaid(Long orderId) {
        OrdersData order = ordersDataRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update payment status
        if (order.getPayment() == null) {
            throw new RuntimeException("Payment not found for this order");
        }
        order.getPayment().setStatus(PaymentStatus.PAID);

        // Change order status to CONFIRMED after payment
        order.setStatus(OrderStatus.CONFIRMED);

        OrdersData savedOrder = ordersDataRepository.save(order);
        return orderDataMapper.toDto(savedOrder);
    }
    
    @Override
    public OrderDataResponseDto updateOrderStatus(Long orderId, String status) {
        OrdersData order = ordersDataRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        try {
            OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(orderStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid order status: " + status);
        }

        OrdersData savedOrder = ordersDataRepository.save(order);
        return orderDataMapper.toDto(savedOrder);
    }



}
