package com.AplicatioEcommerce.EcoomerceAplication.DTO;

import java.math.BigDecimal;

public class ProductDTO {
	
	private String productName;
	private String manufacture;
	private BigDecimal price;
	private Integer quantity;
	
	
	



	public ProductDTO(String productName, String manufacture, BigDecimal price, Integer quantity) {
		super();
		this.productName = productName;
		this.manufacture = manufacture;
		this.price = price;
		this.quantity = quantity;
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
	
	
	

	
	
	

}
