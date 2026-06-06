package com.AplicatioEcommerce.EcoomerceAplication.module.auth.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.RefreshToken;
import com.AplicatioEcommerce.EcoomerceAplication.module.auth.repository.RefreshTokenDao;

import jakarta.transaction.Transactional;

@Service
public class RefreshTokenServiceImplements implements RefreshTokenService {

    @Value("${app.refresh.expiration-days:7}")
    private int expirationDays;

    private final RefreshTokenDao refreshTokenDao;

    public RefreshTokenServiceImplements(RefreshTokenDao refreshTokenDao) {
        this.refreshTokenDao = refreshTokenDao;
    }

    @Override
    @Transactional
    public RefreshToken createRefreshToken(Customer customer) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCustomer(customer);
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(expirationDays));
        refreshToken.setRevoked(false);
        return refreshTokenDao.save(refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken validateAndRotate(String tokenValue) {
        RefreshToken existing = refreshTokenDao.findByToken(tokenValue)
                .orElseThrow(() -> new CustomerException(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND,"Refresh token no encontrado"));

        if (existing.isRevoked()) {
            throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND,"Refresh token revocado");
        }
        if (existing.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND,"Refresh token expirado");
        }

        existing.setRevoked(true);
        refreshTokenDao.save(existing);

        RefreshToken newToken = new RefreshToken();
        newToken.setToken(UUID.randomUUID().toString());
        newToken.setCustomer(existing.getCustomer());
        newToken.setExpiresAt(LocalDateTime.now().plusDays(expirationDays));
        newToken.setRevoked(false);
        return refreshTokenDao.save(newToken);
    }

    @Override
    @Transactional
    public void revokeAllByCustomer(Long customerId) {
        refreshTokenDao.revokeAllByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void revokeAndDeleteByToken(String tokenValue) {
        refreshTokenDao.findByToken(tokenValue).ifPresent(token -> {
            Long customerId = token.getCustomer().getId();
            refreshTokenDao.revokeAllByCustomerId(customerId);
            refreshTokenDao.deleteRevokedByCustomerId(customerId);
        });
    }
}
