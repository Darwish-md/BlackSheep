package com.blacksheep.service;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentCustomerForm {
    private Item[] items;
    private Float totalPrice;
    
    public PaymentCustomerForm() {
	}
    
	public PaymentCustomerForm(Item[] items, Float totalPrice) {
		this.items = items;
		this.totalPrice = totalPrice;
	}
	public Item[] getItems() {
		return items;
	}
	public void setItems(Item[] items) {
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
		return "PaymentCustomerForm [items=" + Arrays.toString(items) + ", totalPrice=" + totalPrice + "]";
	}
    
	
    
    
}