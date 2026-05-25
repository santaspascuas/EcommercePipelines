package com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto;

public class AdressDTOResponse {

    private Long addressId;
    private String street;
    private String number;
    private String floor;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public AdressDTOResponse() {}

    public AdressDTOResponse(Long addressId, String street, String number, String floor,
                              String city, String province, String postalCode, String country) {
        this.addressId = addressId;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

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
}
