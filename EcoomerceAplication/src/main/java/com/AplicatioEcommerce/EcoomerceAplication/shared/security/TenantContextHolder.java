package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TenantContextHolder {

	private static final Logger log = LoggerFactory.getLogger(TenantContextHolder.class);
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

    //Esquema por defecto cuando no hay tenant en el hilo.
    public static final String SYSTEM_TENANT_ID = "public";

    public static void setTenantId(String tenantCode) {
    	log.debug("Estableciendo tenantId en el hilo: {}", tenantCode);
        TENANT_ID.set(tenantCode);
    }

    public static String getTenantId() {
        return TENANT_ID.get();
    }

    //Establece el esquema por defecto para Hibernate cuando no hay tenant en el hilo.
    public static String getTenantIdentifierForHibernate() {
    	String tenantCode = TENANT_ID.get();
    	if(tenantCode == null) {
    		log.debug("No hay tenant en el hilo. Devolviendo SYSTEM_TENANT_ID ({}) para Hibernate.", SYSTEM_TENANT_ID);
            return SYSTEM_TENANT_ID;
    	}

    	return tenantCode;
    }

    //Detecta los null point excepction en la logica de negocio
    public static String getRequiredTenantId() {
        String tenantCode = TENANT_ID.get();
        if (tenantCode == null) throw new IllegalStateException("No hay tenantId en el contexto del tenant");
        return tenantCode;
    }

    public static void clear() {
        TENANT_ID.remove();
    }
}
