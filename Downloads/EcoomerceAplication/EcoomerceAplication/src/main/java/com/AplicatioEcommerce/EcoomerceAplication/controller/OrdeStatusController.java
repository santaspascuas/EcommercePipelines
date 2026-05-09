package com.AplicatioEcommerce.EcoomerceAplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusHistory;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.OrderStatusService;

@RestController
@RequestMapping("/api/orderstatus")
public class OrdeStatusController {

    @Autowired
    private OrderStatusService orderstatusService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<OrderStatusHistory>>> getHistory(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Historial de estados de la orden", orderstatusService.getHistory(id)));
    }

    @GetMapping("/{id}/last")
    public ResponseEntity<ApiResponse<OrderStatusHistory>> getLastStatus(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Ultimo estado de la orden", orderstatusService.getLastStatus(id)));
    }
}
