package com.AplicatioEcommerce.EcoomerceAplication.module.auth.service;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.RefreshToken;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Customer customer);

    RefreshToken validateAndRotate(String tokenValue);

    void revokeAllByCustomer(Long customerId);

    void revokeAndDeleteByToken(String tokenValue);
}
