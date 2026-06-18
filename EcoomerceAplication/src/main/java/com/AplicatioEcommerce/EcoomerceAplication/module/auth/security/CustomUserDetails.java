package com.AplicatioEcommerce.EcoomerceAplication.module.auth.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;

public class CustomUserDetails implements UserDetails {

    private final Customer customer;
    private final String tenantCode;
    private final String schemaName;

    public CustomUserDetails(Customer customer) {
        this.customer = customer;
        this.tenantCode = customer.getTenant().getTenantCode();
        this.schemaName = customer.getTenant().getSchemaName();
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public String getSchemaName() {
        return schemaName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(customer.getRole()));
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return customer.isActive();
    }

    @Override
    public boolean isAccountNonExpired() {
        return  customer.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return  customer.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  customer.isCredentialsNonExpired();
    }
}
