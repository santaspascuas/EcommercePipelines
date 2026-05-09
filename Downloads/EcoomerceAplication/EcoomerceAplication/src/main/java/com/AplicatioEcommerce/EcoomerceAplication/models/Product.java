package com.AplicatioEcommerce.EcoomerceAplication.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.ToString;

@Entity
@Table(name="products")
@ToString
public class Product {
	
	@Id
	@Column(name = "productId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "product_id_generator")
	private Long id;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false, precision = 10, scale = 2)
	@DecimalMin("0.00")
	private BigDecimal price;
	
	private String description;
	
	@Column(nullable = false)
	private String manufacture;
	
	@Column(nullable = false)
	@Min(value = 0)
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private CategoryEnum category;
	
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sellerId", nullable = false)
	@JsonIgnore
	private Seller seller;
	
	public Product() {}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
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

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	

	
	
	
	
	
	

}
