package com.AplicatioEcommerce.EcoomerceAplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;

@Entity
@Table(name="address")
@ToString
public class Address {
	
	@Id
	@Column(name = "addressId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_generator")
	@SequenceGenerator(
		name = "address_id_generator",
		sequenceName = "address_id_seq",
		allocationSize = 1
	)
	private Long addressId;
	
	@Column(nullable = false)
	private String calle;
	
	private String localidad;
	
	private String provincia;
	
	@Pattern(
		    regexp = "^(0[1-9]|[1-4][0-9]|5[0-2])[0-9]{3}$",
		    message = "Código postal inválido"
		)
	private String codigoPostal;
	
	@Column(nullable = false)
	@Pattern(
		    regexp = "[A-Za-zÁÉÍÓÚáéíóúÑñ\\s.-]+",
		    message = "El país solo puede contener letras y espacios"
		)
	private String pais;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private Customer customer;
	
	
	public Address() {}
	


	
	


	public Long getAddressId() {
		return addressId;
	}







	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}







	public Address(Long addressId, String calle, String localidad, String provincia,
			@Pattern(regexp = "^(0[1-9]|[1-4][0-9]|5[0-2])[0-9]{3}$", message = "Código postal inválido") String codigoPostal,
			@Pattern(regexp = "[A-Za-zÁÉÍÓÚáéíóúÑñ\\s.-]+", message = "El país solo puede contener letras y espacios") String pais,
			Customer customer) {
		super();
		this.addressId = addressId;
		this.calle = calle;
		this.localidad = localidad;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
		this.customer = customer;
	}









	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
	
	
	

}
