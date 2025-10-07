package com.foodie.delivery.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class FoodItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long food_id;
	
	@Column(name="food_name")
	private String food_name;
	
	@Column(name="food_price")
	private double price;
	
	@Column(name="food_category")
	private String category;
	
	@ManyToOne
	@JoinColumn(name="restaurent_id", nullable=false)
	private Restaurant restaurant;
	
	@OneToMany(mappedBy="foodItem", cascade = CascadeType.ALL)
	private List<OrderItems> orderItems;

	public FoodItem() {
		super();
	}

	public FoodItem(Long food_id, String food_name, double price, String category, Restaurant restaurant,
			List<OrderItems> orderItems) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.price = price;
		this.category = category;
		this.restaurant = restaurant;
		this.orderItems = orderItems;
	}

	public Long getFood_id() {
		return food_id;
	}

	public void setFood_id(Long food_id) {
		this.food_id = food_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	@Override
	public String toString() {
		return "FoodItem [food_id=" + food_id + ", food_name=" + food_name + ", price=" + price + ", category="
				+ category + "]";
	}
}
