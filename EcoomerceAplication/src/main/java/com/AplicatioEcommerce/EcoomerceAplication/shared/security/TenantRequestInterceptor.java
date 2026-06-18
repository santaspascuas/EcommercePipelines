package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository.TenantDao;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Tenant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantRequestInterceptor implements HandlerInterceptor{

	private static final String TENANT_HEADER = "X-Tenant-ID";

	private static final Pattern TENANT_CODE_PATTERN =
			Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

	private static final Logger log = LoggerFactory.getLogger(TenantRequestInterceptor.class);

	private final TenantDao tenantDao;

	public TenantRequestInterceptor(TenantDao tenantDao) {
		this.tenantDao = tenantDao;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("preHandle---Inicio de la captura");

		if (TenantContextHolder.getTenantId() != null) {
			log.debug("Tenant ya establecido por el filtro de seguridad: {}", TenantContextHolder.getTenantId());
            return true;

		}

		// 2. Si no hay JWT, buscamos si viene por un Header (ej: integraciones API o Webhooks)
        String tenantHeader = request.getHeader(TENANT_HEADER);

        if(tenantHeader != null && !tenantHeader.trim().isEmpty()) {
        String tenantCode = tenantHeader.trim();
        log.debug("Capturamos el header '{}' porque no venía cookie con JWT", tenantCode);
        if (!TENANT_CODE_PATTERN.matcher(tenantCode).matches()) {
            log.error("El formato del header X-Tenant-ID no es un tenantCode válido: {}", tenantCode);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Formato de X-Tenant-ID invalido.");
            return false; // Cortamos la petición aquí mismo
        }

        Tenant tenant = tenantDao.findByTenantCode(tenantCode).orElse(null);
        if (tenant == null) {
            log.error("No existe ningún tenant con el código {}", tenantCode);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Tenant no encontrado.");
            return false;
        }

        TenantContextHolder.setTenantId(tenant.getSchemaName());
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
