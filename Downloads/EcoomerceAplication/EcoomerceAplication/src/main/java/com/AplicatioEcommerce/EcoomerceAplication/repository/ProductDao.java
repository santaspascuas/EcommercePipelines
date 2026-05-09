package com.AplicatioEcommerce.EcoomerceAplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.models.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.models.Product;
import com.AplicatioEcommerce.EcoomerceAplication.models.ProductStatus;

public interface ProductDao extends JpaRepository<Product, Long>{
	
	@Query(
			"select new com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO(p.productName,p.manufacture,p.price,p.quantity)"
			+ " from Product p where p.category =:catenum")
	public List<ProductDTO> getAllProductByCategory(@Param("catenum") CategoryEnum catenum);

	@Query(
			"select new com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO(p.productName,p.manufacture,p.price,p.quantity)"
			+ " from Product p where p.status =:status")
	public List<ProductDTO> getAllProductByStatus(@Param("status") ProductStatus status);

	@Query("""
		       select new com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO(
		           p.productName,
		           p.manufacture,
		           p.price,
		           p.quantity
		       )
		       from Product p
		       where p.seller.sellerId = :sellerId
		       """)
		List<ProductDTO> findProductsBySeller(@Param("sellerId") Long sellerId);
	
		boolean existsBySellerSellerIdAndProductName(Long sellerId, String productName);	
}
