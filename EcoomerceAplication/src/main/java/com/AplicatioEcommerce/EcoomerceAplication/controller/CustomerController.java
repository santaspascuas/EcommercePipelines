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

import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTOResponse>> registrar(
            @Valid @RequestBody CustomerRequestDTO dto) {
        CustomerDTOResponse response = customerService.anadirCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Customer registrado correctamente", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTOResponse>>> getAll() {
        return ResponseEntity.ok(
                ResponseUtil.success("Lista de customers", customerService.getallCustomer()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTOResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO dto) {
        CustomerDTOResponse response = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok(
                ResponseUtil.success("Customer actualizado correctamente", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Customer eliminado correctamente", null));
    }
}
