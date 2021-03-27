package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
  	private Integer id;

	private String code;
	private String description;

	private Long quantity;
	
	private Double price;
	private Double discount;
	private Long availableQuantity;
	
	public Product() {
	}	
	
	public Product(String code, String description, Long quantity, Long availableQuantity, Double price, Double discount) {
		super();
		this.discount = discount;
		this.description = description;
		this.availableQuantity = availableQuantity;
		this.quantity = quantity;
		this.price = price;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Long getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

}
