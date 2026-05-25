package com.AplicatioEcommerce.EcoomerceAplication.shared.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce API")
                        .description("API REST para gestion de customers, sellers, productos y ordenes")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ecommerce Team")));
    }
}
