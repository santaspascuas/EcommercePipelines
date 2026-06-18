package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    private final SchemaMultiTenantConnectionProvider multiTenantConnectionProvider;
    private final SchemaTenantIdentifierResolver tenantIdentifierResolver;

    public HibernateConfig(SchemaMultiTenantConnectionProvider multiTenantConnectionProvider,
                           SchemaTenantIdentifierResolver tenantIdentifierResolver) {
        this.multiTenantConnectionProvider = multiTenantConnectionProvider;
        this.tenantIdentifierResolver = tenantIdentifierResolver;
    }

    @Bean
    HibernatePropertiesCustomizer hibernateMultiTenancyCustomizer() {
        return hibernateProperties -> {
            hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
            hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);
        };
    }
}
