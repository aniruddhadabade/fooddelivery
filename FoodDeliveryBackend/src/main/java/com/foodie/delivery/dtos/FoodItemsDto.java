package com.foodie.delivery.dtos;

public class FoodItemsDto {
	private Long food_id;
	private String food_name;
	private double price;
	private String category;
	
	public FoodItemsDto() {
		super();
	}

	public FoodItemsDto(Long food_id, String food_name, double price, String category) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.price = price;
		this.category = category;
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

	@Override
	public String toString() {
		return "FoodItemsDto [food_id=" + food_id + ", food_name=" + food_name + ", price=" + price + ", category="
				+ category + "]";
	}
}
