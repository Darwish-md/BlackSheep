package com.blacksheep.POJOS;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentCustomerForm {
    private List<Item> items;
    private Float totalPrice;
    
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

	
    
	
    
    
}