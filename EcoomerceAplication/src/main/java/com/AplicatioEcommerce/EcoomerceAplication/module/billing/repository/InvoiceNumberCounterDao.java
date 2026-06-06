package com.AplicatioEcommerce.EcoomerceAplication.module.billing.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceNumberCounter;

import java.util.Optional;

public interface InvoiceNumberCounterDao extends JpaRepository<InvoiceNumberCounter, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM InvoiceNumberCounter c WHERE c.customer.id = :customerId AND c.series = :series AND c.year = :year")
    Optional<InvoiceNumberCounter> findForUpdate(
            @Param("customerId") Long customerId,
            @Param("series") String series,
            @Param("year") int year);
}
