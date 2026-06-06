package com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Invoice;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatus;

import java.util.List;
import java.util.Optional;

public interface InvoiceDao extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomerId(Long customerId);

    Page<Invoice> findByCustomerId(Long customerId, Pageable pageable);

    Page<Invoice> findByCustomerIdAndStatus(Long customerId, InvoiceStatus status, Pageable pageable);

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
}
