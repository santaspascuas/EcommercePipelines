package com.AplicatioEcommerce.EcoomerceAplication.module.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.RefreshToken;

public interface RefreshTokenDao extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query("UPDATE RefreshToken r SET r.revoked = true WHERE r.customer.id = :customerId")
    void revokeAllByCustomerId(@Param("customerId") Long customerId);

    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.customer.id = :customerId AND r.revoked = true")
    void deleteRevokedByCustomerId(@Param("customerId") Long customerId);
}
