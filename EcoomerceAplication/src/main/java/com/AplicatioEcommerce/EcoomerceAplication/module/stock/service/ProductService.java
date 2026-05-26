package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;

public interface ProductService {

    ProductDTO anadirproductoToCatalogo(ProductDTOCreate product);

    Product getProductFromCatalogById(Long id);

    Product updateProductInCatalog(Long id, ProductDTOCreate product);

    void deleteProductFromCatalog(Long id);

    List<ProductDTO> getAllProductsInCatalog();

    PageResult<Product> getProductsPage(PageParam pageParam);

    List<ProductDTO> getProductsOfCategory( CategoryEnum category);

    List<Product> getProductsByActiveStatus( boolean active);
}
