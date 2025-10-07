package com.foodie.delivery.dtos;

public class UserResponseDto {
	private Long rid;
	private String userName;
	private String name;
	private String email;
	private String address;
	private String phoneNumber;
	
	public UserResponseDto() {
		super();
	}

	public UserResponseDto(long rid, String userName, String name, String email, String address, String phoneNumber) {
		super();
		this.rid = rid;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
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
		return "UserResponseDto [rid=" + rid + ", userName=" + userName + ", name=" + name + ", email=" + email
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
}
