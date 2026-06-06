package com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.category = :category AND p.active = true")
    List<Product> findByCategoryAndActiveTrue(@Param("category") CategoryEnum category);
	@Query("SELECT p FROM Product p WHERE p.active = :active")
	List<Product> findByActiveActive(@Param("active") Boolean active);
    List<Product> findByCustomerId(Long customerId);
    Page<Product> findByCustomerId(Long customerId, Pageable pageable);
    boolean existsByCustomerIdAndName(Long customerId, String name);
    boolean existsByCode(String code);
}
