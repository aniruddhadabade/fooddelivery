package com.foodie.delivery.dtos;

public class OrderItemsRequestDto {
    private Long foodItemId;
    private Long quantity;  
    private double price;
    
	public OrderItemsRequestDto() {
		super();
	}

	public OrderItemsRequestDto(Long foodItemId, Long quantity, double price) {
		super();
		this.foodItemId = foodItemId;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(Long foodItemId) {
		this.foodItemId = foodItemId;
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
		return "OrderItemsRequestDto [foodItemId=" + foodItemId + ", quantity=" + quantity + ", price=" + price + "]";
	}     
}
