package com.foodie.delivery.dtos;


public class RestaurantDto {
	private Long restaurentId;
	private String rname;
	private String raddress;
	private String contact_number;
	private String email;
	private String city;
	private String state;
	private String country;
	
	public RestaurantDto() {
		super();
	}

	public RestaurantDto(long restaurentId, String rname, String raddress, String contact_number, String email,
			String city, String state, String country) {
		super();
		this.restaurentId = restaurentId;
		this.rname = rname;
		this.raddress = raddress;
		this.contact_number = contact_number;
		this.email = email;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public long getRestaurentId() {
		return restaurentId;
	}

	public void setRestaurentId(long restaurentId) {
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

	@Override
	public String toString() {
		return "RestaurantDto [restaurentId=" + restaurentId + ", rname=" + rname + ", raddress=" + raddress
				+ ", contact_number=" + contact_number + ", email=" + email + ", city=" + city + ", state=" + state
				+ ", country=" + country + "]";
	}
}
