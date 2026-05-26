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
	public ProductDTO anadirproductoToCatalogo(ProductDTOCreate product) {
		//No pasamos id porque lo pasa el hilo o theth. Captura ese detalle
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("[{} Inicio del metodo]", method);
		
		if(productdao.existsByCode(product.getCode())) {
			log.error("Existe ese codigo para el producto");
			//Si existe el codigo con el producto.
			throw new ProductException("Ya hay un producto registrado con el ticket"); 	
		}
		//Mapeamos y creamos
		Product producto = ProductssMapper.fromCreate(product);
		log.debug("{Product producto}", producto.toString());
		//Ahora guardamos
		Product savedProduct = productdao.save(producto);
		// TODO Auto-generated method stub
		
		log.info("Fin del insert");
		return ProductssMapper.toResponseFront(savedProduct);
	}

	@Override
	public List<ProductDTO> getAllProductsInCatalog() {
		log.info("[getAllProductsInCatalog Inicio del método--1]");
		return productdao.findAll()
				.stream().
				map(ProductssMapper::toResponseFront).toList();
		//Stream con for para recorrerlo y luego mapeas.
	}

	@Override
	public PageResult<Product> getProductsPage(PageParam pageParam) {
		
		return null;
	}

	@Override
	public List<ProductDTO> getProductsOfCategory(CategoryEnum category) {
		// TODO Auto-generated method stub
		return productdao.findByCategoryAndActiveTrue(category)
				.stream()
				.map(ProductssMapper::toResponseFront).toList();
	}

	@Override
	public List<Product> getProductsByActiveStatus(boolean active) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductFromCatalogById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProductInCatalog(Long id, ProductDTOCreate product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductFromCatalog(Long id) {
		// TODO Auto-generated method stub
		
	}
}
