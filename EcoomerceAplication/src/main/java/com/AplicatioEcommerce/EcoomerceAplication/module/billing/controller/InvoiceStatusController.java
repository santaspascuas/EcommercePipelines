package com.AplicatioEcommerce.EcoomerceAplication.module.billing.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.InvoiceStatusHistory;
import com.AplicatioEcommerce.EcoomerceAplication.module.billing.service.InvoiceStatusService;

@RestController
@RequestMapping("/api/invoices/{invoiceId}/history")
public class InvoiceStatusController {

    private final InvoiceStatusService invoiceStatusService;

    public InvoiceStatusController(InvoiceStatusService invoiceStatusService) {
        this.invoiceStatusService = invoiceStatusService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<InvoiceStatusHistory>>> getHistory(
            @PathVariable Long invoiceId) {
        return ResponseEntity.ok(
                ResponseUtil.success("Historial de la factura",
                        invoiceStatusService.getHistory(invoiceId)));
    }

    @GetMapping("/last")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<InvoiceStatusHistory>> getLastStatus(
            @PathVariable Long invoiceId) {
        return ResponseEntity.ok(
                ResponseUtil.success("Último estado de la factura",
                        invoiceStatusService.getLastStatus(invoiceId)));
    }
}
