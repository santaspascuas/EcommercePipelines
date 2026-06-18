package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer{
	
	private static final Logger log = LoggerFactory.getLogger(WebMvcConfig.class);
	
	private final TenantRequestInterceptor tenantInterceptor;
	
	public WebMvcConfig(TenantRequestInterceptor tenantInterceptor) {
		this.tenantInterceptor = tenantInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tenantInterceptor);
	}
	
	
	

}
