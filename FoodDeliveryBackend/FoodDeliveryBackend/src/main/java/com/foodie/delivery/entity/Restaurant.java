package com.foodie.delivery.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurent_id")
	private Long restaurentId;
	
	@Column(name="restaurant_name")
	private String rname;
	
	@Column(name="restaurant_address")
	private String raddress;
	
	@Column(name="restaurant_contact")
	private String contact_number;
	
	@Column(name="restaurant_email", unique=true)
	private String email;
	
	@Column(name="restaurant_city")
	private String city;
	
	@Column(name="restaurant_state")
	private String state;
	
	@Column(name="restaurant_country")
	private String country;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<FoodItem> foodItems;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<OrdersData> orderData;

	public Restaurant() {
		super();
	}

	public Restaurant(Long restaurentId, String rname, String raddress, String contact_number, String email,
			String city, String state, String country, List<FoodItem> foodItems, List<OrdersData> orderData) {
		super();
		this.restaurentId = restaurentId;
		this.rname = rname;
		this.raddress = raddress;
		this.contact_number = contact_number;
		this.email = email;
		this.city = city;
		this.state = state;
		this.country = country;
		this.foodItems = foodItems;
		this.orderData = orderData;
	}

	public Long getRestaurentId() {
		return restaurentId;
	}

	public void setRestaurentId(Long restaurentId) {
		this.restaurentId = restaurentId;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRaddress() {
		return raddress;
	}

	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(List<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}

	public List<OrdersData> getOrderData() {
		return orderData;
	}

	public void setOrderData(List<OrdersData> orderData) {
		this.orderData = orderData;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurentId=" + restaurentId + ", rname=" + rname + ", raddress=" + raddress
				+ ", contact_number=" + contact_number + ", email=" + email + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", foodItems=" + foodItems + ", orderData=" + orderData + "]";
	}
}
