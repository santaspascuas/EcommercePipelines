package com.AplicatioEcommerce.EcoomerceAplication.mappers;

import java.math.BigDecimal;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.models.Product;
import com.AplicatioEcommerce.EcoomerceAplication.models.ProductStatus;
import com.AplicatioEcommerce.EcoomerceAplication.models.Seller;

public class ProductssMapper {
	
	public static ProductDTO toResponseFront(Product producto) {
		
		ProductDTO response = new ProductDTO(
				producto.getProductName(),
				producto.getManufacture(),
				producto.getPrice(),
				producto.getQuantity()
				);
		return response;		
	}
	
	public static Product frontalCreation(Seller seller , ProductDTOCreate product) {
		
		Product producto = new Product();
		producto.setProductName(product.getProductName());
		producto.setPrice(product.getPrice());
		producto.setManufacture(product.getManufacture());
		producto.setQuantity(product.getQuantity());
		producto.setCategory(product.getCategory());
		producto.setStatus(ProductStatus.DISPONIBLE);
		producto.setSeller(seller);
		return producto;	 
	}
	
	
	
	
	
	
	
	
	
	
	
}
