package com.AplicatioEcommerce.EcoomerceAplication.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name="Cart")
@ToString
public class Cart {
	
	@Id
	@Column(name = "cartId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_generator")
	@SequenceGenerator(
		name = "cart_id_generator",
		sequenceName = "cart_id_seq",
		allocationSize = 1
	)
	private Long cartId;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;


	
	
	

}
