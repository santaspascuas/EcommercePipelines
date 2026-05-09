package com.AplicatioEcommerce.EcoomerceAplication.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class CartItem {
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	public CartItem() {}

	public CartItem(Cart cart, Product product) {
		super();
		this.cart = cart;
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	
	
	
	
	
	
	
}



