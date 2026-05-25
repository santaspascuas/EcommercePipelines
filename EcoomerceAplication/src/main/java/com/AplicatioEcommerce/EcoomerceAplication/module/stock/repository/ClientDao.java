package com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.customer.id = :customerId")
    List<Client> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT c FROM Client c WHERE c.customer.id = :customerId")
    Page<Client> findByCustomerIdPaged(@Param("customerId") Long customerId, Pageable pageable);

    boolean existsByCustomerIdAndEmail(Long customerId, String email);

    boolean existsByCustomerIdAndNif(Long customerId, String nif);
}
