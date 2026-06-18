package com.AplicatioEcommerce.EcoomerceAplication.shared.security;


import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

 @Component
public class SchemaTenantIdentifierResolver implements CurrentTenantIdentifierResolver<String>{

	@Override
	public  String resolveCurrentTenantIdentifier() {
		//Aqui vamos a leer el tenant desde el contextHolder (ya devuelve "public" por defecto)
		return TenantContextHolder.getTenantIdentifierForHibernate();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}



}
