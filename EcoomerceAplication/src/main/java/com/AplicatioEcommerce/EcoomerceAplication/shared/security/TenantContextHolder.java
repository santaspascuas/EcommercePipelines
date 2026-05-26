package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AplicatioEcommerce.EcoomerceAplication.module.auth.service.AuthService;

public class TenantContextHolder {
	
	private static final Logger log = LoggerFactory.getLogger(TenantContextHolder.class);
    private static final ThreadLocal<Long> CUSTOMER_ID = new ThreadLocal<>();

    public static void setCustomerId(Long customerId) {
    	log.debug("setCustomerId",customerId );
        CUSTOMER_ID.set(customerId);
    }

    public static Long getCustomerId() {
        return CUSTOMER_ID.get();
    }
    
    //Detecta los null point excepction.s
    
    public static Long getRequiredCustomerId() {
        Long id = CUSTOMER_ID.get();
        if (id == null) throw new IllegalStateException("No hay customerId en el contexto del tenant");
        return id;
    }

    public static void clear() {
        CUSTOMER_ID.remove();
    }
}
