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
public class UserRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_id")
	private Long rid;
	
	@Column(unique = true)
	private String userName;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String address;
	
	private String phoneNumber;
	
	@OneToMany(mappedBy="userRegistration", cascade = CascadeType.ALL)
	private List<OrdersData> orderData;

	public UserRegistration() {
		super();
	}

	public UserRegistration(Long rid, String userName, String name, String email, String password, String address,
			String phoneNumber, List<OrdersData> orderData) {
		super();
		this.rid = rid;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.orderData = orderData;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<OrdersData> getOrderData() {
		return orderData;
	}

	public void setOrderData(List<OrdersData> orderData) {
		this.orderData = orderData;
	}

	@Override
	public String toString() {
		return "UserRegistration [rid=" + rid + ", userName=" + userName + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", address=" + address + ", phoneNumber=" + phoneNumber + ", orderData="
				+ orderData + "]";
	}
}
