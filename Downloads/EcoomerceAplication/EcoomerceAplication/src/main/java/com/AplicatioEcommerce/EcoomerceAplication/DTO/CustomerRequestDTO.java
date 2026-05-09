package com.AplicatioEcommerce.EcoomerceAplication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustomerRequestDTO {

	@NotBlank
	@Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in first name")
	private String firstName;

	@NotBlank
	@Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in lastName")
	private String lastName;

	@NotBlank
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
	private String password;

	@NotBlank
	private String iban;
	
	
	public CustomerRequestDTO() {}

	public CustomerRequestDTO(String firstName, String lastName, String mobileNumber, String email, String password,
			String iban) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.iban = iban;
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


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}


	@Override
	public String toString() {
		return "CustomerRequestDTO [firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", email=" + email + ", password=" + password + ", iban=" + iban + "]";
	}	
	
	

}
