package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ProductException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ServiceException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.security.AuthenticatedCustomerResolver;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.mapper.ProductssMapper;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.ProductDao;

import jakarta.transaction.Transactional;

@Service
public class ProductImplements implements ProductService {

    @Autowired
    private ProductDao productdao;

    private static final Logger log = LoggerFactory.getLogger(ProductImplements.class);

	@Override
	@Transactional
	public ProductDTO anadirproductoToCatalogo(ProductDTOCreate product) {
		//No pasamos id porque lo pasa el hilo o theth. Captura ese detalle
		
		log.info("[anadirproductoToCatalogo] Inicio del método");
		
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
		log.info("[getProductsPage] Solicitando página de productos multi-schema");
        // Usamos el findAll(Pageable) nativo de Spring Data, ya filtrado por el esquema de la conexión
        return new PageResult<>(productdao.findAll(pageParam.toPageable()));
	}

	@Override
	public List<ProductDTO> getProductsOfCategory(CategoryEnum category) {
		log.info("[List<ProductDTO> getProductsOfCategory]");
		return productdao.findByCategoryAndActiveTrue(category)
				.stream()
				.map(ProductssMapper::toResponseFront).toList();
	}

	@Override
	public List<ProductDTO> getProductsByActiveStatus(boolean active) {
		log.info("[getProductsByActiveStatus] Filtrando por estado activo: {}", active);
		return productdao.findByActive(active)
				.stream()
				.map(ProductssMapper::toResponseFront).toList();
	}

	@Override
	public ProductDTO getProductFromCatalogById(Long id) {
		log.info("[getProductFromCatalogById] Buscando producto ID: {}", id);
		Product producto = productdao.findById(id).orElseThrow(
			() -> new ProductException("El producto no existe en tu catálogo.")
		);
		return ProductssMapper.toResponseFront(producto);
	}

	@Override
	public Product updateProductInCatalog(Long id, ProductDTOCreate product) {
		log.info("[updateProductInCatalog] Iniciando la actualizacion por ID: {}", id);
		Product producto = productdao.findById(id).orElseThrow(
			() -> new ProductException("El producto no existe en la database")
		);
		if (!producto.getCode().equalsIgnoreCase(product.getCode()) && productdao.existsByCode(product.getCode())) {
			log.error("[updateProductInCatalog] Intento de duplicar código de producto: {}", product.getCode());
			throw new ProductException("Ya tienes otro producto registrado con el código: " + product.getCode());
		}
		ProductssMapper.updateEntity(producto, product);
		return productdao.save(producto);
	}

	@Override
	public void deleteProductFromCatalog(Long id) {
		log.info("[deleteProductFromCatalog] Eliminando producto ID: {}", id);
		Product producto = productdao.findById(id).orElseThrow(
			() -> new ProductException("El producto no existe en la database")
		);
		productdao.delete(producto);
	}

	private void checkOwnership(Long ownerCustomerId) {
		Long currentCustomerId = AuthenticatedCustomerResolver.getCustomerId();
		if (currentCustomerId != null && !currentCustomerId.equals(ownerCustomerId)) {
			throw new ServiceException(GlobalErrorCodeConstants.FORBIDDEN);
		}
	}
}
