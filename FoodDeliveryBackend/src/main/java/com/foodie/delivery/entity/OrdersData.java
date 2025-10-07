package com.foodie.delivery.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.foodie.delivery.config.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class OrdersData {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	
	@ManyToOne
	@JoinColumn(name="registration_id",nullable=false)
	private UserRegistration userRegistration;
	
	@ManyToOne
	@JoinColumn(name="restaurent_id", nullable=false)
	private Restaurant restaurant;
	
	@OneToMany(mappedBy="orderdata", cascade = CascadeType.ALL)
	private List<OrderItems> orderItems;
	
	private LocalDateTime date;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToOne(mappedBy = "orderdata", cascade = CascadeType.ALL)
	private Payment payment;

	public OrdersData() {
		super();
	}

	public OrdersData(Long orderId, UserRegistration userRegistration, Restaurant restaurant,
			List<OrderItems> orderItems, LocalDateTime date, OrderStatus status, Payment payment) {
		super();
		this.orderId = orderId;
		this.userRegistration = userRegistration;
		this.restaurant = restaurant;
		this.orderItems = orderItems;
		this.date = date;
		this.status = status;
		this.payment = payment;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public UserRegistration getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "OrdersData [orderId=" + orderId + ", userRegistration=" + userRegistration + ", restaurant="
				+ restaurant + ", date=" + date + ", status=" + status + ", payment=" + payment + "]";
	}
}
