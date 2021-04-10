package com.blacksheep.domain;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderItemId implements Serializable{

	private static final long serialVersionUID = -770856908208323295L;
	private Product product;
	private Order order;
	
	public OrderItemId() {
		
	}
	public OrderItemId(Product product, Order order) {
		this.product = product;
		this.order = order;
	}
	@ManyToOne
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@ManyToOne
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	public void setProduct(Optional<Product> product) {
		// TODO Auto-generated method stub
	}
	@Override
	public String toString() {
		return "OrderItemId [product=" + product + ", order=" + order + "]";
	}
	
	
}
