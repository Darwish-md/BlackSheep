package com.blacksheep.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {
	
	private OrderItemId pk;
	private Float unitPrice;
	private int quantity;
	
	@EmbeddedId
	public OrderItemId getPk() {
		return pk;
	}
	public void setPk(OrderItemId pk) {
		this.pk = pk;
	}
	public Float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItem [pk=" + pk + ", unitPrice=" + unitPrice + ", quantity=" + quantity + "]";
	}
	
	
	
}
