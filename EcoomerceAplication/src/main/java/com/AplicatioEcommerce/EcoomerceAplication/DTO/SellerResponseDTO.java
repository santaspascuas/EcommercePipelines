package com.AplicatioEcommerce.EcoomerceAplication.DTO;

public class SellerResponseDTO {

    private Long sellerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    public SellerResponseDTO() {}

    public SellerResponseDTO(Long sellerId, String firstName, String lastName,
                              String email, String mobileNumber) {
        this.sellerId = sellerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Long getSellerId() { return sellerId; }
    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
}
