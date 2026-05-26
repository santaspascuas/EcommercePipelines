package com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	//Al ser multinenta. La inyeccion de la query va directa en el hilo.
	@Query("SELECT p FROM Product p WHERE p.category = :category AND p.active = true")
    List<Product> findByCategoryAndActiveTrue( @Param("category") CategoryEnum category);
    List<Product> findProductsByCustomer(@Param("customerId") Long customerId);
    boolean existsByCustomerIdAndName(String name);
    boolean existsByCode(String code);
}
