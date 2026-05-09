package com.AplicatioEcommerce.EcoomerceAplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusValues;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> crear(
            @Valid @RequestBody OrderRequestDTO dto) {
        OrderResponseDTO response = orderService.crearOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Orden creada correctamente", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Orden encontrada", orderService.getOrderById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getAll() {
        return ResponseEntity.ok(
                ResponseUtil.success("Lista de ordenes", orderService.getAllOrders()));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getByCustomer(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                ResponseUtil.success("Ordenes del customer", orderService.getOrdersByCustomer(customerId)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @RequestParam OrderStatusValues status) {
        return ResponseEntity.ok(
                ResponseUtil.success("Estado de la orden actualizado", orderService.updateOrderStatus(id, status)));
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelar(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Orden cancelada correctamente", null));
    }
}
