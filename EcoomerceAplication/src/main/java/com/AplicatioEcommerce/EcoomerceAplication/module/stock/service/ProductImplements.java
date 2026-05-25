package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ProductException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ServiceException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.security.TenantContextHolder;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.mapper.ProductssMapper;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository.CustomerDao;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.ProductDao;

import jakarta.transaction.Transactional;

@Service
public class ProductImplements implements ProductService {

    @Autowired
    private ProductDao productdao;

    @Autowired
    private CustomerDao customerdao;

    private static final Logger log = LoggerFactory.getLogger(ProductImplements.class);

    @Override
    @Transactional
    public ProductDTO anadirproductoToCatalogo(Long customerId, ProductDTOCreate product) {
        log.info("[anadirproductoToCatalogo] customerId {}", customerId);

        Customer customer = customerdao.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("No existe el autónomo con id " + customerId));

        if (productdao.existsByCustomerIdAndName(customerId, product.getName())) {
            throw new ProductException(GlobalErrorCodeConstants.PRODUCT_NAME_DUPLICATE);
        }
        if (product.getCode() != null && productdao.existsByCode(product.getCode())) {
            throw new ProductException(GlobalErrorCodeConstants.PRODUCT_CODE_DUPLICATE, "Código: " + product.getCode());
        }

        Product producto = ProductssMapper.fromCreate(customer, product);
        productdao.save(producto);
        log.info("[anadirproductoToCatalogo] Fin OK - id {}", producto.getId());
        return ProductssMapper.toResponseFront(producto);
    }

    @Override
    public Product getProductFromCatalogById(Long id) {
        log.info("[getProductFromCatalogById] id {}", id);
        Product product = productdao.findById(id)
                .orElseThrow(() -> new ProductException(GlobalErrorCodeConstants.PRODUCT_NOT_FOUND, "id: " + id));
        checkOwnership(product.getCustomer().getId());
        return product;
    }

    @Override
    @Transactional
    public Product updateProductInCatalog(Long idProducto, ProductDTOCreate product) {
        log.info("[updateProductInCatalog] id {}", idProducto);
        Product producto = productdao.findById(idProducto)
                .orElseThrow(() -> new ProductException(GlobalErrorCodeConstants.PRODUCT_NOT_FOUND, "id: " + idProducto));
        checkOwnership(producto.getCustomer().getId());
        ProductssMapper.updateEntity(producto, product);
        productdao.save(producto);
        return producto;
    }

    @Override
    @Transactional
    public String deleteProductFromCatalog(Long id) {
        log.info("[deleteProductFromCatalog] id {}", id);
        Product producto = productdao.findById(id)
                .orElseThrow(() -> new ProductException(GlobalErrorCodeConstants.PRODUCT_NOT_FOUND, "id: " + id));
        checkOwnership(producto.getCustomer().getId());
        productdao.deleteById(producto.getId());
        return "Producto " + id + " eliminado correctamente";
    }

    @Override
    public List<Product> getAllProductsInCatalog(Long customerId) {
        return productdao.findProductsByCustomer(customerId);
    }

    @Override
    public List<Product> getAllProductsOfCustomer(Long customerId) {
        return productdao.findProductsByCustomer(customerId);
    }

    @Override
    public List<Product> getProductsOfCategory(Long customerId, CategoryEnum category) {
        return productdao.getAllProductByCategory(customerId, category);
    }

    @Override
    public List<Product> getProductsByActiveStatus(Long customerId, boolean active) {
        return productdao.getAllProductByActiveStatus(customerId, active);
    }

    private void checkOwnership(Long ownerCustomerId) {
        Long tenantId = TenantContextHolder.getCustomerId();
        if (tenantId != null && !tenantId.equals(ownerCustomerId)) {
            throw new ServiceException(GlobalErrorCodeConstants.FORBIDDEN);
        }
    }

    @Override
    public PageResult<Product> getProductsPage(Long customerId, PageParam pageParam) {
        log.info("[getProductsPage] customerId={}", customerId);
        return new PageResult<>(
                productdao.findProductsByCustomerPaged(customerId, pageParam.toPageable(Sort.by("name").ascending()))
        );
    }
}
