package com.foodie.delivery.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDataResponseDto {
    private Long orderId;
    private Long userId;
    private Long restaurantId;
    private String status;
    private LocalDateTime date;
    private List<OrderItemResponse> items;
    private Double totalAmount;      // total including GST
    private String paymentStatus;
	public OrderDataResponseDto() {
		super();
	}
	public OrderDataResponseDto(Long orderId, Long userId, Long restaurantId, String status, LocalDateTime date,
			List<OrderItemResponse> items, Double totalAmount, String paymentStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.status = status;
		this.date = date;
		this.items = items;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public List<OrderItemResponse> getItems() {
		return items;
	}
	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Override
	public String toString() {
		return "OrderDataResponseDto [orderId=" + orderId + ", userId=" + userId + ", restaurantId=" + restaurantId
				+ ", status=" + status + ", date=" + date + ", totalAmount=" + totalAmount + ", paymentStatus="
				+ paymentStatus + "]";
	}
}
