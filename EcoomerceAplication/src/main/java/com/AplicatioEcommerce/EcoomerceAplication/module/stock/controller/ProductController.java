package com.AplicatioEcommerce.EcoomerceAplication.module.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ProductDTOCreate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Product;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/customer/{customerId}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<ProductDTO>> crear(
            @PathVariable Long customerId,
            @RequestBody ProductDTOCreate dto) {
        ProductDTO response = productService.anadirproductoToCatalogo(customerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Producto creado correctamente", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Producto encontrado", productService.getProductFromCatalogById(id)));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<PageResult<Product>>> getByCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Catálogo de productos", productService.getProductsPage(customerId, pageParam)));
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

    @GetMapping("/customer/{customerId}/category/{category}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<List<Product>>> getByCategory(
            @PathVariable Long customerId,
            @PathVariable CategoryEnum category) {
        return ResponseEntity.ok(
                ResponseUtil.success("Productos por categoría", productService.getProductsOfCategory(customerId, category)));
    }

    @GetMapping("/customer/{customerId}/active/{active}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<List<Product>>> getByActiveStatus(
            @PathVariable Long customerId,
            @PathVariable boolean active) {
        return ResponseEntity.ok(
                ResponseUtil.success("Productos por estado", productService.getProductsByActiveStatus(customerId, active)));
    }
}
