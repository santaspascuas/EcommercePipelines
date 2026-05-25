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

    @Query("SELECT p FROM Product p WHERE p.customer.id = :customerId AND p.category = :category AND p.active = true")
    List<Product> getAllProductByCategory(@Param("customerId") Long customerId, @Param("category") CategoryEnum category);

    @Query("SELECT p FROM Product p WHERE p.customer.id = :customerId AND p.active = :active")
    List<Product> getAllProductByActiveStatus(@Param("customerId") Long customerId, @Param("active") boolean active);

    @Query("SELECT p FROM Product p WHERE p.customer.id = :customerId")
    List<Product> findProductsByCustomer(@Param("customerId") Long customerId);

    @Query("SELECT p FROM Product p WHERE p.customer.id = :customerId")
    Page<Product> findProductsByCustomerPaged(@Param("customerId") Long customerId, Pageable pageable);

    boolean existsByCustomerIdAndName(Long customerId, String name);

    boolean existsByCode(String code);
}
