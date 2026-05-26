package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantRequestInterceptor implements HandlerInterceptor{
	
	private static final String TENANT_HEADER = "X-Tenant-ID";
	
	private static final Logger log = LoggerFactory.getLogger(TenantRequestInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		log.info("preHandle---Inicio de la captura");
		
		if (TenantContextHolder.getCustomerId() != null) {
			log.debug("Tenant ya establecido por el filtro de seguridad: {}", TenantContextHolder.getCustomerId());
            return true;
            
		}
        
		// 2. Si no hay JWT, buscamos si viene por un Header (ej: integraciones API o Webhooks)
        String tenantHeader = request.getHeader(TENANT_HEADER);
        
        if(tenantHeader != null && tenantHeader.trim().isEmpty()) { 
        log.debug("Capturamos el header '{}' porque no venía cookie con JWT", tenantHeader);
        try {
            Long tenantId = Long.parseLong(tenantHeader);
            TenantContextHolder.setCustomerId(tenantId);
        }catch (NumberFormatException e) {
            log.error("El formato del header X-Tenant-ID no es un Long válido: {}", tenantHeader);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Formato de X-Tenant-ID invalido.");
            return false; // Cortamos la petición aquí mismo
        	}
        
        }

        return true; // Permitimos que la petición continúe al controlador
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		TenantContextHolder.clear();
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}


	
	
	
	

	
	
	
	
	

}
