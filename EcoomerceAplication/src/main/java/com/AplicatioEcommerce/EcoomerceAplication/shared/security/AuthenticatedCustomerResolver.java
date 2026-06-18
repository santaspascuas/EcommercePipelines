package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.AplicatioEcommerce.EcoomerceAplication.module.auth.security.CustomUserDetails;

public class AuthenticatedCustomerResolver {

    private AuthenticatedCustomerResolver() {}

    public static Long getCustomerId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getCustomer().getId();
        }
        return null;
    }
}
