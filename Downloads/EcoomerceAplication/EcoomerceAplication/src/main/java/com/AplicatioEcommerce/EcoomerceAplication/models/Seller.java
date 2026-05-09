package com.AplicatioEcommerce.EcoomerceAplication.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;

@Entity
@Table(name="sellers")
@ToString
public class Seller {
	
	
	@Id
	@Column(name = "sellerId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seller_id_generator")
	private Long sellerId;
	
	@Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in first name")
	@Column(nullable = false)
	private String firstName;
	
	@Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in last name")
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
	private String password;
	
	@Column(nullable = false, unique =true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;
	
	@Column(nullable = false, unique =true)
	@Email
	private String email;

	@OneToMany
	@JsonIgnore
	private List<Product> product;
	
	
	
	public Seller() {}
	

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	
	

	
	
	
	
	

}
