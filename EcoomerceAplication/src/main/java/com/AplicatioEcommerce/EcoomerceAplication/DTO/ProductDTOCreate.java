package com.AplicatioEcommerce.EcoomerceAplication.DTO;

import java.math.BigDecimal;

import com.AplicatioEcommerce.EcoomerceAplication.models.CategoryEnum;

public class ProductDTOCreate {
	
	private String productName;
	private String manufacture;
	private BigDecimal price;
	private Integer quantity;
	private CategoryEnum category;
	
	public ProductDTOCreate(String productName, String manufacture, BigDecimal price, Integer quantity,
			CategoryEnum category) {
		super();
		this.productName = productName;
		this.manufacture = manufacture;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public CategoryEnum getCategory() {
		return category;
	}
	public void setCategory(CategoryEnum category) {
		this.category = category;
	}
	
	
	
	
	
	
	
	

}
