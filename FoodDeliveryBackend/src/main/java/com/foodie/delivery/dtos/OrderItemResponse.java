package com.foodie.delivery.dtos;

public class OrderItemResponse {
    private Long itemId;       
    private Long orderId;      
    private Long foodItemId;   
    private String foodName;   
    private Long quantity;
    private double price;
	public OrderItemResponse() {
		super();
	}
	public OrderItemResponse(Long itemId, Long orderId, Long foodItemId, String foodName, Long quantity, double price) {
		super();
		this.itemId = itemId;
		this.orderId = orderId;
		this.foodItemId = foodItemId;
		this.foodName = foodName;
		this.quantity = quantity;
		this.price = price;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(Long foodItemId) {
		this.foodItemId = foodItemId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
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
		return "OrderItemResponse [itemId=" + itemId + ", orderId=" + orderId + ", foodItemId=" + foodItemId
				+ ", foodName=" + foodName + ", quantity=" + quantity + ", price=" + price + "]";
	}
}