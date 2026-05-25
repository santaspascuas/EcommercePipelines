package com.AplicatioEcommerce.EcoomerceAplication.module.billing.mapper;

import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceItemResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Invoice;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceItem;

import java.util.List;

public class InvoiceMapper {

    private InvoiceMapper() {}

    public static InvoiceResponseDTO toResponse(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setSeries(invoice.getSeries());
        dto.setIssueDate(invoice.getIssueDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setStatus(invoice.getStatus() != null ? invoice.getStatus().name() : null);
        dto.setSubtotal(invoice.getSubtotal());
        dto.setTaxAmount(invoice.getTaxAmount());
        dto.setIrpfAmount(invoice.getIrpfAmount());
        dto.setTotal(invoice.getTotal());
        dto.setPaymentMethod(invoice.getPaymentMethod());
        dto.setPaymentReference(invoice.getPaymentReference());
        dto.setNotes(invoice.getNotes());

        if (invoice.getCustomer() != null) {
            dto.setCustomerEmail(invoice.getCustomer().getEmail());
        }
        if (invoice.getClient() != null) {
            dto.setClientId(invoice.getClient().getClientId());
            dto.setClientLegalName(invoice.getClient().getLegalName());
            dto.setClientNif(invoice.getClient().getNif());
        }
        if (invoice.getAddress() != null) {
            dto.setAddressId(invoice.getAddress().getAddressId());
        }
        if (invoice.getItems() != null) {
            List<InvoiceItemResponseDTO> items = invoice.getItems().stream()
                    .map(InvoiceMapper::toItemResponse)
                    .toList();
            dto.setItems(items);
        }
        return dto;
    }

    private static InvoiceItemResponseDTO toItemResponse(InvoiceItem item) {
        InvoiceItemResponseDTO dto = new InvoiceItemResponseDTO();
        dto.setInvoiceItemId(item.getInvoiceItemId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setTaxRate(item.getTaxRate());
        dto.setIrpf(item.getIrpf());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }
}
