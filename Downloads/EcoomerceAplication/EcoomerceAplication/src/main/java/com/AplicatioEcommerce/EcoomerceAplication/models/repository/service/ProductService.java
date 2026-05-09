package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.models.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.models.Product;
import com.AplicatioEcommerce.EcoomerceAplication.models.ProductStatus;

public interface ProductService {
	
	
	//INTERNAMENTE
	
	public ProductDTO anadirproductoToCatalogo(Long id, ProductDTOCreate product);
	
	//Casos internos
	
    Product getProductFromCatalogById(Long id);

    Product updateProductInCatalog(Long id,ProductDTOCreate product);

    String deleteProductFromCatalog(Long id);

    List<Product> getAllProductsInCatalog();
    
    //frontal
    List<ProductDTO> getAllProductsOfSeller(Long sellerId);

    List<ProductDTO> getProductsOfCategory(CategoryEnum category);

    List<ProductDTO> getProductsOfStatus(ProductStatus status);

}
