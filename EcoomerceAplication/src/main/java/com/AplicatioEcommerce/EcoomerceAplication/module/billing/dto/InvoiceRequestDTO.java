package com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class InvoiceRequestDTO {

    @NotNull(message = "El ID del autónomo es obligatorio")
    private Long customerId;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clientId;

    private Long addressId;

    @Size(max = 5)
    private String series = "F";

    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate issueDate;

    private LocalDate dueDate;

    @Size(max = 100)
    private String paymentMethod;

    @Size(max = 2000)
    private String notes;

    @NotEmpty(message = "La factura debe tener al menos una línea")
    @Valid
    private List<InvoiceItemRequestDTO> items;

    public InvoiceRequestDTO() {}

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<InvoiceItemRequestDTO> getItems() { return items; }
    public void setItems(List<InvoiceItemRequestDTO> items) { this.items = items; }
}
