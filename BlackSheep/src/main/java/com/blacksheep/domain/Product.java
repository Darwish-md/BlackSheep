package com.blacksheep.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "product")
public class Product {

	private long id;
	private String name;
	private int unitPrice;
	private int stockQuantity;
	private String imageSrc;
	private String category;
	private String categoryGender;

	public Product() {
	}

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

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	@NotNull
	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@NotNull
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@NotNull
	public String getCategoryGender() {
		return categoryGender;
	}

	public void setCategoryGender(String categoryGender) {
		this.categoryGender = categoryGender;
	}

	@NotNull
	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", unitPrice=" + unitPrice + ", stockQuantity=" + stockQuantity
				+ ", imageSrc=" + imageSrc + ", category=" + category + ", categoryGender=" + categoryGender + "]";
	}

}
