package com.AplicatioEcommerce.EcoomerceAplication.shared.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_generator")
    @SequenceGenerator(name = "address_id_generator", sequenceName = "address_id_seq", allocationSize = 1)
    private Long addressId;

    @Column(nullable = false)
    private String street;

    private String number;

    private String floor;

    private String city;

    private String province;

    @Pattern(
            regexp = "^(0[1-9]|[1-4][0-9]|5[0-2])[0-9]{3}$",
            message = "Código postal inválido"
    )
    private String postalCode;

    @Column(nullable = false)
    @Pattern(
            regexp = "[A-Za-zÁÉÍÓÚáéíóúÑñ\\s.-]+",
            message = "El país solo puede contener letras y espacios"
    )
    private String country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    public Address() {}

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
