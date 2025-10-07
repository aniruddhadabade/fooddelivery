package com.foodie.delivery.dtos;

public class UserRequestDto {
	private Long id;
	private String userName;
	private String name;
	private String email;
	private String password;
	private String address;
	private String phoneNumber;
	
	public UserRequestDto() {
		super();
	}

	public UserRequestDto(long id, String userName, String name, String email, String password, String address,
			String phoneNumber) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "UserRequestDto [id=" + id + ", userName=" + userName + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
}
