package com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.tenant WHERE c.email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);
}
