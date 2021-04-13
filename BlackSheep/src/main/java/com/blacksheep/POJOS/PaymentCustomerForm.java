package com.blacksheep.POJOS;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentCustomerForm {
    private List<Item> items;
    private Float totalPrice;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    
    public PaymentCustomerForm() {
	}
    
	public PaymentCustomerForm( List<Item> items, Float totalPrice) {
		this.items = items;
		this.totalPrice = totalPrice;
	}
	public  List<Item> getItems() {
		return items;
	}
	public void setItems( List<Item> items) {
		this.items = items;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "PaymentCustomerForm [items=" + items + ", totalPrice=" + totalPrice + "]";
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
}