package com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceResponseDTO {

    private Long invoiceId;
    private String invoiceNumber;
    private String series;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String status;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal irpfAmount;
    private BigDecimal total;
    private String paymentMethod;
    private String paymentReference;
    private String notes;
    private String customerEmail;
    private Long clientId;
    private String clientLegalName;
    private String clientNif;
    private Long addressId;
    private List<InvoiceItemResponseDTO> items;

    public InvoiceResponseDTO() {}

    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }

    public BigDecimal getIrpfAmount() { return irpfAmount; }
    public void setIrpfAmount(BigDecimal irpfAmount) { this.irpfAmount = irpfAmount; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentReference() { return paymentReference; }
    public void setPaymentReference(String paymentReference) { this.paymentReference = paymentReference; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public String getClientLegalName() { return clientLegalName; }
    public void setClientLegalName(String clientLegalName) { this.clientLegalName = clientLegalName; }

    public String getClientNif() { return clientNif; }
    public void setClientNif(String clientNif) { this.clientNif = clientNif; }

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public List<InvoiceItemResponseDTO> getItems() { return items; }
    public void setItems(List<InvoiceItemResponseDTO> items) { this.items = items; }
}
