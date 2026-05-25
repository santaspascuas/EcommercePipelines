package com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileDao extends JpaRepository<CustomerProfile, Long> {

    Optional<CustomerProfile> findByCustomerId(Long customerId);

    boolean existsByPhone(String phone);
    boolean existsByNif(String nif);

    boolean existsByPhoneAndIdNot(String phone, Long customerId);
    boolean existsByNifAndIdNot(String nif, Long customerId);
}
