package com.blacksheep.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

enum Category {
	WATCH, SHIRT, JACKET, ACCESSORY;
}

enum CategoryGender {
	MEN, WOMEN, KIDS, UNISEX;
}

@Entity
@Table(name = "product")
public class Product {

	private long id;
	private String name;
	private int unitPrice;
	private int stockQuantity;
	private Category category;
	private CategoryGender categoryGender;
	
	public Product() {}	

	public Product(String name, int unitPrice, int stockQuantity, Category category, CategoryGender categoryGender) {
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
	Category getCategory() {
		return category;
	}
	void setCategory(Category category) {
		this.category = category;
	}
	CategoryGender getCategoryGender() {
		return categoryGender;
	}
	void setCategoryGender(CategoryGender categoryGender) {
		this.categoryGender = categoryGender;
	}
	
}
