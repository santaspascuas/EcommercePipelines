package com.AplicatioEcommerce.EcoomerceAplication.module.billing.service;

import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatus;

public interface InvoiceService {

    // Crea borrador — sin número de factura aún
    InvoiceResponseDTO createDraft(InvoiceRequestDTO dto);

    // Emite la factura — asigna número correlativo
    InvoiceResponseDTO issue(Long invoiceId);

    InvoiceResponseDTO getById(Long invoiceId);

    PageResult<InvoiceResponseDTO> getInvoicesPage(Long customerId, PageParam pageParam);

    PageResult<InvoiceResponseDTO> getInvoicesByStatus(Long customerId, InvoiceStatus status, PageParam pageParam);

    InvoiceResponseDTO updateStatus(Long invoiceId, InvoiceStatus status);

    void cancel(Long invoiceId);
}
