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
            log.debug("Authentication auth", auth.getCredentials());
            if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            	//Sie entra aqui es que que esta como identificado en el contexto.
                TenantContextHolder.setCustomerId(userDetails.getCustomer().getId());
            }
            filterChain.doFilter(request, response);
        } finally {
        	//Limpia el hilo de ejecucion.
            TenantContextHolder.clear();
        }
    }
}
