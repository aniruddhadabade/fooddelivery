package com.foodie.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.foodie.delivery.config.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderDataRequestDto {

    @NotNull(message = "RestaurantId is required")
    private Long restaurantId;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemsRequestDto> items;

    @NotNull(message = "Payment method is required")
    private String paymentMethod;

    public OrderDataRequestDto() {
        super();
    }

    public OrderDataRequestDto(Long restaurantId, List<OrderItemsRequestDto> items, String paymentMethod) {
        this.restaurantId = restaurantId;
        this.items = items;
        this.paymentMethod = paymentMethod;
    }

    public @NotNull(message = "RestaurantId is required") Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(@NotNull(message = "RestaurantId is required") Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public @NotEmpty(message = "Order must have at least one item") List<OrderItemsRequestDto> getItems() {
        return items;
    }

    public void setItems(@NotEmpty(message = "Order must have at least one item") List<OrderItemsRequestDto> items) {
        this.items = items;
    }

    public @NotNull(message = "Payment method is required") String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@NotNull(message = "Payment method is required") String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "OrderDataRequestDto{" +
                "restaurantId=" + restaurantId +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
