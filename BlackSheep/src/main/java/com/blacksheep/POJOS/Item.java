package com.blacksheep.POJOS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	private Long id;
	private String name;
	private Float price;
	private String imageSrc;
	private int no;
	
	public Item() {
	}
	
	public Item(Long id, String name, Float price, String imageSrc, int no) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageSrc = imageSrc;
		this.no = no;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", imageSrc=" + imageSrc + ", no=" + no + "]";
	}
	
	
	
}
