 package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.AplicatioEcommerce.EcoomerceAplication.module.auth.security.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class TenantContextFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(TenantContextFilter.class);
	

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
        	log.info("[doFilterInternal] Inicio");
        	//Verificamos la autenticacon del usuario con el springcecurity
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            
            if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            	log.debug("Authentication auth", auth.getCredentials());
            	//Sie entra aqui es que que esta como identificado en el contexto.
                TenantContextHolder.setTenantId(userDetails.getSchemaName());
            }else {
            	//Si hay rutas publicas-> no hay contexto y crashea.
            	log.debug("[TenantContextFilter] Ruta pública o sin autenticar. Continuando en el esquema global (PUBLIC).");
            	
            }
            filterChain.doFilter(request, response);
        }catch(Exception e) {
        	log.error("[TenantContextFilter] Error inesperado en el filtro de inquilinos", e);
            throw e; // Lanzamos para que no se quede el error oculto
        }        
        finally {
        	//Limpia el hilo de ejecucion.
            TenantContextHolder.clear();
        }
    }
}
