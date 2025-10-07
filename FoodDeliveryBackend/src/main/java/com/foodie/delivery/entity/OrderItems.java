package com.foodie.delivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItems {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long item_id;
	
	@ManyToOne
	@JoinColumn(name = "orderId", nullable = false)
	private OrdersData orderdata;
	
	@ManyToOne
	@JoinColumn(name="food_id", nullable = false)
	private FoodItem foodItem;
	
	private Long quantity;
	
	private double price;

	public OrderItems() {
		super();
	}

	public OrderItems(Long item_id, OrdersData orderdata, FoodItem foodItem, Long quantity, double price) {
		super();
		this.item_id = item_id;
		this.orderdata = orderdata;
		this.foodItem = foodItem;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public OrdersData getOrderdata() {
		return orderdata;
	}

	public void setOrderdata(OrdersData orderdata) {
		this.orderdata = orderdata;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItems [item_id=" + item_id + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
