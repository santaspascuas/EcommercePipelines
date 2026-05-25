package com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InvoiceItemRequestDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer quantity;

    public InvoiceItemRequestDTO() {}

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
