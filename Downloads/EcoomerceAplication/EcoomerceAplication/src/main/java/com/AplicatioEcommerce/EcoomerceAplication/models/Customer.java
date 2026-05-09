package com.AplicatioEcommerce.EcoomerceAplication.models;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name="customers")
@ToString
public class Customer {
	@Id
	@Column(name = "customerId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "customer_id_generator")
	@SequenceGenerator(
		    name = "customer_id_generator",
		    sequenceName = "customer_id_seq",
		    allocationSize = 1
		)
	private Long id;
	
	@Column(nullable = false)
	@Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in first name")
	private String firstName;
	
	@Column(nullable = false)
	@Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in lastName")
	private String lastName;
	
	@Column(nullable = false, unique =true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;
	
	@Column(nullable = false, unique =true)
	@Email
	private String email;
	
	
	@Column(nullable = false)
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,11}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
	private String password;
	
	private LocalDateTime altausuario;
	
	@Column(nullable = false, unique =true)
	private String iban;
	
	//Mapa de direcciones.->Un diccionario para obtener las direcciones, se guarda como tabla intermedia.
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // Sino mapeas hibernate crea tabla intermedia.
	private List<Order> orders = new ArrayList<>();
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Cart cart;


	public Customer() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getAltausuario() {
		return altausuario;
	}

	public void setAltausuario(LocalDateTime altausuario) {
		this.altausuario = altausuario;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", email=" + email + ", password=" + password + ", altausuario=" + altausuario
				+ ", iban=" + iban + "]";
	}
	
	
	
	
	
	
	
	

}
