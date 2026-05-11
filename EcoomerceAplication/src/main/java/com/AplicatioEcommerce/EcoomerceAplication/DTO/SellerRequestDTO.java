package com.AplicatioEcommerce.EcoomerceAplication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SellerRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Nombre solo puede contener letras")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Apellido solo puede contener letras")
    private String lastName;

    @NotBlank(message = "La contrasena es obligatoria")
    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Contrasena debe tener 8-15 caracteres")
    private String password;

    @NotBlank(message = "El telefono es obligatorio")
    @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Telefono movil espanol invalido")
    private String mobileNumber;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email invalido")
    private String email;

    public SellerRequestDTO() {}

    public SellerRequestDTO(String firstName, String lastName, String password,
                             String mobileNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
