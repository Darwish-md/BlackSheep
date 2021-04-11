package com.blacksheep.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product {

	private long id;
	private String name;
	private int unitPrice;
	private int stockQuantity;
	/*@Enumerated(EnumType.STRING)*/
	private String category;
	private String categoryGender;
	private String imageSrc;
	
	
	public Product() {}	

	public Product(String name, int unitPrice, int stockQuantity, String category, String categoryGender) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.stockQuantity = stockQuantity;
		this.category = category;
		this.categoryGender = categoryGender;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryGender() {
		return categoryGender;
	}
	public void setCategoryGender(String categoryGender) {
		this.categoryGender = categoryGender;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	
}
