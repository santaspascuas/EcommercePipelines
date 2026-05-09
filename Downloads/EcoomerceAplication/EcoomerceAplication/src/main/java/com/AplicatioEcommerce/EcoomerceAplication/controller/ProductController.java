package com.AplicatioEcommerce.EcoomerceAplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.models.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.models.Product;
import com.AplicatioEcommerce.EcoomerceAplication.models.ProductStatus;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<ApiResponse<ProductDTO>> crear(
            @PathVariable Long sellerId,
            @RequestBody ProductDTOCreate dto) {
        ProductDTO response = productService.anadirproductoToCatalogo(sellerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Producto creado correctamente", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Producto encontrado", productService.getProductFromCatalogById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        return ResponseEntity.ok(
                ResponseUtil.success("Catalogo de productos", productService.getAllProductsInCatalog()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> actualizar(
            @PathVariable Long id,
            @RequestBody ProductDTOCreate dto) {
        return ResponseEntity.ok(
                ResponseUtil.success("Producto actualizado", productService.updateProductInCatalog(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Producto eliminado", productService.deleteProductFromCatalog(id)));
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getBySeller(@PathVariable Long sellerId) {
        return ResponseEntity.ok(
                ResponseUtil.success("Productos del seller", productService.getAllProductsOfSeller(sellerId)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getByCategory(@PathVariable CategoryEnum category) {
        return ResponseEntity.ok(
                ResponseUtil.success("Productos por categoria", productService.getProductsOfCategory(category)));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getByStatus(@PathVariable ProductStatus status) {
        return ResponseEntity.ok(
                ResponseUtil.success("Productos por estado", productService.getProductsOfStatus(status)));
    }
}
