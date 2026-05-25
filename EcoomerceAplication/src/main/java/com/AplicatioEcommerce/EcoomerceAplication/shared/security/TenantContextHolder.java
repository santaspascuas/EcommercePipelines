package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

public class TenantContextHolder {

    private static final ThreadLocal<Long> CUSTOMER_ID = new ThreadLocal<>();

    public static void setCustomerId(Long customerId) {
        CUSTOMER_ID.set(customerId);
    }

    public static Long getCustomerId() {
        return CUSTOMER_ID.get();
    }

    public static Long getRequiredCustomerId() {
        Long id = CUSTOMER_ID.get();
        if (id == null) throw new IllegalStateException("No hay customerId en el contexto del tenant");
        return id;
    }

    public static void clear() {
        CUSTOMER_ID.remove();
    }
}
