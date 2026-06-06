package com.AplicatioEcommerce.EcoomerceAplication.module.billing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.dto.InvoiceResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatus;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.service.InvoiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    @PreAuthorize("authentication.principal.customer.id == #dto.customerId")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> createDraft(
            @Valid @RequestBody InvoiceRequestDTO dto) {
        InvoiceResponseDTO response = invoiceService.createDraft(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Borrador de factura creado", response));
    }

    @PostMapping("/{id}/issue")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> issue(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Factura emitida correctamente", invoiceService.issue(id)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Factura encontrada", invoiceService.getById(id)));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<PageResult<InvoiceResponseDTO>>> getByCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Facturas del autónomo", invoiceService.getInvoicesPage(customerId, pageParam)));
    }

    @GetMapping("/customer/{customerId}/status/{status}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<PageResult<InvoiceResponseDTO>>> getByStatus(
            @PathVariable Long customerId,
            @PathVariable InvoiceStatus status,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Facturas filtradas por estado",
                        invoiceService.getInvoicesByStatus(customerId, status, pageParam)));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> updateStatus(
            @PathVariable Long id,
            @RequestParam InvoiceStatus status) {
        return ResponseEntity.ok(
                ResponseUtil.success("Estado actualizado", invoiceService.updateStatus(id, status)));
    }

    @DeleteMapping("/{id}/cancel")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long id) {
        invoiceService.cancel(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Factura cancelada", null));
    }
}
