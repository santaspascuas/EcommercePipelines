package com.AplicatioEcommerce.EcoomerceAplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.models.Seller;

public interface SellerDao extends JpaRepository<Seller, Long> {

    Optional<Seller> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);
}
