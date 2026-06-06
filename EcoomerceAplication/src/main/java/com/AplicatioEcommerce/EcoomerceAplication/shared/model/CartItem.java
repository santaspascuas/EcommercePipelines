package com.AplicatioEcommerce.EcoomerceAplication.shared.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name="CartItem")
@ToString
public class CartItem {
	
	@Id
	@Column(name = "cartItem")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItem_id_generator")
	@SequenceGenerator(
		name = "cartItem_id_generator",
		sequenceName = "cartItem_id_seq",
		allocationSize = 1
	)
	private Long cartItemId;
	
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



