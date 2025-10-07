package com.foodie.delivery.entity;

import com.foodie.delivery.config.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long payment_id;
	
	@OneToOne
	@JoinColumn(name = "orderId", nullable = false, unique = true)
	private OrdersData orderdata;
	
	private Double amount;
	
	private String payment_method;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	public Payment() {
		super();
	}

	public Payment(Long payment_id, OrdersData orderdata, Double amount, String payment_method, PaymentStatus status) {
		super();
		this.payment_id = payment_id;
		this.orderdata = orderdata;
		this.amount = amount;
		this.payment_method = payment_method;
		this.status = status;
	}

	public Long getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Long payment_id) {
		this.payment_id = payment_id;
	}

	public OrdersData getOrderdata() {
		return orderdata;
	}

	public void setOrderdata(OrdersData orderdata) {
		this.orderdata = orderdata;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", amount=" + amount + ", payment_method=" + payment_method
				+ ", status=" + status + "]";
	}
}
