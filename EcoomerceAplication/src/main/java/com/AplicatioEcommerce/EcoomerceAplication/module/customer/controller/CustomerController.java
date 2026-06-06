package com.AplicatioEcommerce.EcoomerceAplication.module.customer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerAuthUpdateDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CustomerDTOResponse>> registrar(
            @Valid @RequestBody CustomerRequestDTO dto) {
        CustomerDTOResponse response = customerService.anadirCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Customer registrado correctamente", response));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PageResult<CustomerDTOResponse>>> getAll(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Lista de customers", customerService.getCustomerPage(pageParam)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("authentication.principal.customer.id == #id or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CustomerDTOResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CustomerAuthUpdateDTO dto) {
        CustomerDTOResponse response = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok(
                ResponseUtil.success("Credenciales actualizadas correctamente", response));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Customer eliminado correctamente", null));
    }
}
