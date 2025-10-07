package com.foodie.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.foodie.delivery.config.OrderStatus;

public class OrderDataRequestDto {
	private Long userId;
    private Long restaurantId;
    private OrderStatus status;  
    private LocalDateTime date;
    private List<OrderItemsRequestDto> items; 
    private String paymentMethod;
	public OrderDataRequestDto(Long userId, Long restaurantId, OrderStatus status, LocalDateTime date,
			List<OrderItemsRequestDto> items, String paymentMethod) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.status = status;
		this.date = date;
		this.items = items;
		this.paymentMethod = paymentMethod;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public List<OrderItemsRequestDto> getItems() {
		return items;
	}
	public void setItems(List<OrderItemsRequestDto> items) {
		this.items = items;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	@Override
	public String toString() {
		return "OrderDataRequestDto [userId=" + userId + ", restaurantId=" + restaurantId + ", status=" + status
				+ ", date=" + date + ", paymentMethod=" + paymentMethod + "]";
	}
}
