package com.AplicatioEcommerce.EcoomerceAplication.shared.security;


import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaTenantIdentifierResolver implements CurrentTenantIdentifierResolver<String>{
	
	//Esquema de la database para el hilo por defecto
	private static final String DEFAULT_TENANT = "public";

	@Override
	public  String resolveCurrentTenantIdentifier() {
		//Aqui vamos a leer el tentt desde el contextHolder
		Long tenantId = TenantContextHolder.getCustomerId();
		
		if(tenantId != null) {
			return tenantId.toString();
		}
		
		
		return DEFAULT_TENANT;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		// TODO Auto-generated method stub
		return true;
	}



}
