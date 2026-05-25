package com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatusHistory;

import java.util.List;

public interface InvoiceStatusDao extends JpaRepository<InvoiceStatusHistory, Long> {

    List<InvoiceStatusHistory> findByInvoiceInvoiceIdOrderByTimestampAsc(Long invoiceId);

    InvoiceStatusHistory findTopByInvoiceInvoiceIdOrderByTimestampDesc(Long invoiceId);
}
