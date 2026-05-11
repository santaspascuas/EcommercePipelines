package com.AplicatioEcommerce.EcoomerceAplication.DTO;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class OrderRequestDTO {

    @NotNull(message = "El ID del customer es obligatorio")
    private Long customerId;

    @NotNull(message = "El ID de la direccion es obligatorio")
    private Long addressId;

    @Pattern(regexp = "[0-9]{16}", message = "El numero de tarjeta debe tener 16 digitos")
    private String cardNumber;

    @NotEmpty(message = "La orden debe contener al menos un producto")
    @Valid
    private List<OrderItemRequestDTO> items;

    public OrderRequestDTO() {}

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public List<OrderItemRequestDTO> getItems() { return items; }
    public void setItems(List<OrderItemRequestDTO> items) { this.items = items; }
}
