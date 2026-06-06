package com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class CustomerAuthUpdateDTO {

    @Email(message = "Email inválido")
    private String email;

    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}",
            message = "La contraseña debe tener entre 8 y 15 caracteres: A-Z, a-z, 0-9 o !@#$%^&*_")
    private String password;

    public CustomerAuthUpdateDTO() {}

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

  
}
