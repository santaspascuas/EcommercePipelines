package com.AplicatioEcommerce.EcoomerceAplication.module.billing.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatus;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatusHistory;

public interface InvoiceStatusService {

    InvoiceStatusHistory addStatus(Long invoiceId, InvoiceStatus status);

    List<InvoiceStatusHistory> getHistory(Long invoiceId);

    InvoiceStatusHistory getLastStatus(Long invoiceId);
}
