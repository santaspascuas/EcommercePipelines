package com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Address;

public interface AddressDao extends JpaRepository<Address, Long> {
    Optional<Address> findById(Long id);
    List<Address> findListByCustomerId(Long customerId);
    Page<Address> findByCustomerId(Long customerId, Pageable pageable);
    Optional<Address> findByCustomerId(Long customerId);
}
