package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.exception.ProductException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.SellerException;
import com.AplicatioEcommerce.EcoomerceAplication.mappers.ProductssMapper;
import com.AplicatioEcommerce.EcoomerceAplication.models.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.models.Product;
import com.AplicatioEcommerce.EcoomerceAplication.models.ProductStatus;
import com.AplicatioEcommerce.EcoomerceAplication.models.Seller;
import com.AplicatioEcommerce.EcoomerceAplication.repository.ProductDao;
import com.AplicatioEcommerce.EcoomerceAplication.repository.SellerDao;

@Service
public class ProductImplements implements ProductService{
	
	
	@Autowired
	private ProductDao productdao;
	@Autowired
	private SellerDao sellerdao;
	
	private static final Logger log = LoggerFactory.getLogger(ProductImplements.class);
	

	@Override
	public ProductDTO anadirproductoToCatalogo(Long idseller, ProductDTOCreate product) {
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("[{}] Inicio", method);
        log.debug("Se recibe el producto:()->",product);
        //La idea es que un vendedor tiene muchos productos->Debemos comprobar si existe el vendedor.
        
        Seller seller = sellerdao.findById(idseller).orElseThrow(
        		()->new SellerException("Seller no encontrado"));
        
        //cONFIRMAS QUE ESTA EL VENDEDOR PERO METES DOS VECES PELOTA DE GOMA
        if(productdao.existsBySellerSellerIdAndProductName(idseller, product.getProductName())) {
        	//Si es true. confirmas que estas metiendo un producto duplicado
        	throw new ProductException("El producto ya existe para este vendedor con ese nombre");
        }
        
        Product producto = ProductssMapper.frontalCreation(seller, product);
        if(producto == null) {
        	throw new ProductException("El producto no se ha creado correctamente con el DTO");
        }
        productdao.save(producto);
        
        return ProductssMapper.toResponseFront(producto);     
	}

	@Override
	public Product getProductFromCatalogById(Long id) {
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("[{}] Inicio", method);
        log.debug("Se recibe el id()->",id);
		Product producto = productdao.findById(id).orElseThrow(
				()-> new ProductException("No se encuentra ese producto en la database"));	
		return producto;
	}

	@Override
	public Product updateProductInCatalog(Long idProducto, ProductDTOCreate product) {
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("[{}] Inicio", method);
        log.debug("Recibo del frontal:Long idProducto", idProducto);
        log.debug("Recibo del frontal:Product product",product );
		
		Product producto = productdao.findById(idProducto).orElseThrow(
				()->new ProductException("No existe ese produycto '%s' en la database"));
		
		producto.setProductName(product.getProductName());
		producto.setPrice(product.getPrice());
		producto.setManufacture(product.getManufacture());
		producto.setQuantity(product.getQuantity());
		producto.setCategory(product.getCategory());
		log.debug("SE ACTUALIZA EL PRODUCTO", producto);
		productdao.save(producto);	
		return producto;
	}

	@Override
	public String deleteProductFromCatalog(Long id) {
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("[{}] Inicio", method);
        //Mas profesional.
        Product producto = productdao.findById(id).orElseThrow(
        		()->new ProductException("El producto no existe para ser borrado"));
		productdao.deleteById(producto.getId());
		
		return "Se ha borrado correctamente el producto" +id;	
		
	}

	@Override
	public List<Product> getAllProductsInCatalog() {
		
		return productdao.findAll();
	}

	@Override
	public List<ProductDTO> getAllProductsOfSeller(Long sellerId) {
		return productdao.findProductsBySeller(sellerId);
	}

	@Override
	public List<ProductDTO> getProductsOfCategory(CategoryEnum category) {
		return productdao.getAllProductByCategory(category);
	}

	@Override
	public List<ProductDTO> getProductsOfStatus(ProductStatus status) {
		return productdao.getAllProductByStatus(status);
	}

}
