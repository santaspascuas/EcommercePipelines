package com.AplicatioEcommerce.EcoomerceAplication.module.stock.mapper;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;

public class ProductssMapper {
	

	
	

    public static ProductDTO toResponseFront(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCode(product.getCode());
        dto.setDescription(product.getDescription());
        dto.setDefaultPrice(product.getDefaultPrice());
        dto.setUnit(product.getUnit());
        dto.setCategory(product.getCategory());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public static Product fromCreate(ProductDTOCreate dto) {
        Product product = new Product();
        
        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setDescription(dto.getDescription());
        product.setDefaultPrice(dto.getDefaultPrice());
        product.setUnit(dto.getUnit());
        product.setCategory(dto.getCategory());
        
        return product;
        
    }

    public static void updateEntity(Product product, ProductDTOCreate dto) {
        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setDescription(dto.getDescription());
        product.setDefaultPrice(dto.getDefaultPrice());
        product.setUnit(dto.getUnit());
        product.setCategory(dto.getCategory());
    }
}
