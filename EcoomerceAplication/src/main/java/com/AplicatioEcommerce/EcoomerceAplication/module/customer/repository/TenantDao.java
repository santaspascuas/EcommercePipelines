package com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Tenant;

public interface TenantDao extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findByTenantCode(String tenantCode);
}
