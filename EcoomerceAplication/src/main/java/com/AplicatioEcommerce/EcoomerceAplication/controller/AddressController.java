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

import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<AdressDTOResponse>> anadir(
            @PathVariable Long customerId,
            @RequestBody AdressDTOUpdate dto) {
        AdressDTOResponse response = addressService.addAddressToCustomer(customerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Direccion anadida correctamente", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdressDTOResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseUtil.success("Direccion encontrada", addressService.getAddress(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdressDTOResponse>>> getAll() {
        return ResponseEntity.ok(
                ResponseUtil.success("Lista de direcciones", addressService.getAllAddresses()));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<AdressDTOResponse>>> getByCustomer(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                ResponseUtil.success("Direcciones del customer", addressService.getAddressesByCustomer(customerId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdressDTOResponse>> actualizar(
            @PathVariable Long id,
            @RequestBody AdressDTOUpdate dto) {
        return ResponseEntity.ok(
                ResponseUtil.success("Direccion actualizada", addressService.updateAddress(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(
                ResponseUtil.success("Direccion eliminada correctamente", null));
    }
}
