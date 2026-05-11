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

import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.SellerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<ApiResponse<SellerResponseDTO>> registrar(
            @Valid @RequestBody SellerRequestDTO dto) {
        SellerResponseDTO response = sellerService.anadirSeller(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Seller registrado correctamente", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SellerResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Seller encontrado", sellerService.getSellerById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SellerResponseDTO>>> getAll() {
        return ResponseEntity.ok(
                ResponseUtil.success("Lista de sellers", sellerService.getAllSellers()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SellerResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SellerRequestDTO dto) {
        return ResponseEntity.ok(
                ResponseUtil.success("Seller actualizado correctamente", sellerService.updateSeller(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Seller eliminado correctamente", null));
    }
}
