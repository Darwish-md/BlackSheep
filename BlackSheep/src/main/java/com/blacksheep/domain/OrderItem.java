package com.blacksheep.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "order_item")
public class OrderItem {
	
	private OrderItemId pk;
	private Float unitPrice;
	private int quantity;
	
	public OrderItem() {
		super();
	}

	public OrderItem(OrderItemId pk, Float unitPrice, int quantity) {
		super();
		this.pk = pk;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	@EmbeddedId
	public OrderItemId getPk() {
		return pk;
	}
	public void setPk(OrderItemId pk) {
		this.pk = pk;
	}
	
	@NotNull
	public Float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@NotNull
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
