package com.AplicatioEcommerce.EcoomerceAplication.DTO;

public class CustomerDTOResponse {
	
	private String firstName;
	private String email;
	private String iban;
	
	
	public CustomerDTOResponse(String firstName, String email, String iban) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.iban = iban;
	}
	
	public CustomerDTOResponse() {}
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	
	
	

}
