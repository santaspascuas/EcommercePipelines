package com.AplicatioEcommerce.EcoomerceAplication.module.stock.controller;

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
@PreAuthorize("isAuthenticated()")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDTO>> crear(@Valid @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO response = clientService.addClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Cliente de facturación registrado correctamente", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Cliente encontrado", clientService.getClientById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<ClientResponseDTO>>> getByCustomer(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Clientes del autónomo", clientService.getClientsPage(pageParam)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO dto) {
        return ResponseEntity.ok(
                ResponseUtil.success("Cliente actualizado correctamente", clientService.updateClient(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Cliente eliminado correctamente", null));
    }
}
