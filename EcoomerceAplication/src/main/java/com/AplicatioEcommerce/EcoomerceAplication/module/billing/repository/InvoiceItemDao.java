package com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao extends JpaRepository<InvoiceItem, Long> {

    List<InvoiceItem> findByInvoiceInvoiceId(Long invoiceId);
}
