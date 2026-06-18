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
	// 1. Buscar por categoría (Solo traerá las de ESTE restaurante automáticamente)
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.active = true")
    List<Product> findByCategoryAndActiveTrue(@Param("category") CategoryEnum category);

    // 2. Buscar por estado activo/inactivo (Señor Tip: Spring Data JPA lo genera solo si sigues la convención de nombres)
    List<Product> findByActive(Boolean active);

    // 3. Buscar todos los productos (Reemplaza al viejo findByCustomerId. Al usar Pageable, te devuelve la paginación del restaurante actual)
    Page<Product> findAll(Pageable pageable);

    // 4. Validar si ya existe un producto con el mismo nombre en este restaurante
    boolean existsByName(String name);

    // 5. Validar si ya existe el código de barras/producto
    boolean existsByCode(String code);
}
