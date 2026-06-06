package com.AplicatioEcommerce.EcoomerceAplication.module.stock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/customer/{customerId}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> crear(
            @PathVariable Long customerId,
            @Valid @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO response = clientService.addClient(customerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Cliente de facturación registrado correctamente", response));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Cliente encontrado", clientService.getClientById(id)));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<PageResult<ClientResponseDTO>>> getByCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Clientes del autónomo", clientService.getClientsPage(customerId, pageParam)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO dto) {
        return ResponseEntity.ok(
                ResponseUtil.success("Cliente actualizado correctamente", clientService.updateClient(id, dto)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Cliente eliminado correctamente", null));
    }
}
