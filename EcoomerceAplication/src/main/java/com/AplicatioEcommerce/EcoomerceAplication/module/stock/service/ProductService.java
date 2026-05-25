package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;

public interface ProductService {

    ProductDTO anadirproductoToCatalogo(Long customerId, ProductDTOCreate product);

    Product getProductFromCatalogById(Long id);

    Product updateProductInCatalog(Long id, ProductDTOCreate product);

    String deleteProductFromCatalog(Long id);

    List<Product> getAllProductsInCatalog(Long customerId);

    List<Product> getAllProductsOfCustomer(Long customerId);

    PageResult<Product> getProductsPage(Long customerId, PageParam pageParam);

    List<Product> getProductsOfCategory(Long customerId, CategoryEnum category);

    List<Product> getProductsByActiveStatus(Long customerId, boolean active);
}
