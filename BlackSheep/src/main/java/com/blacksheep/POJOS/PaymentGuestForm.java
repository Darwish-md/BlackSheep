package com.blacksheep.POJOS;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentGuestForm {
	private List<Item> items;
	private Float totalPrice;
	private String fullName;
	private String email;
	private String streetAddress;
	private String city;
	private String state;
	private String postalCode;

	public PaymentGuestForm() {
		super();
	}

	public PaymentGuestForm(List<Item> items, Float totalPrice, String fullName, String email, String streetAddress,
			String city, String state, String postalCode) {
		super();
		this.items = items;
		this.totalPrice = totalPrice;
		this.fullName = fullName;
		this.email = email;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "PaymentGuestForm [items=" + items + ", totalPrice=" + totalPrice + ", fullName=" + fullName + ", email="
				+ email + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + "]";
	}

}
