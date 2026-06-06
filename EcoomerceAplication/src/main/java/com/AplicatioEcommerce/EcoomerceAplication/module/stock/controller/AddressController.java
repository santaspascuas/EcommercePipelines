package com.AplicatioEcommerce.EcoomerceAplication.module.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/customer/{customerId}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PageResult<AdressDTOResponse>>> getAll(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Lista de direcciones", addressService.getAllAddressesPage(pageParam)));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("authentication.principal.customer.id == #customerId")
    public ResponseEntity<ApiResponse<PageResult<AdressDTOResponse>>> getByCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        return ResponseEntity.ok(
                ResponseUtil.success("Direcciones del customer", addressService.getAddressesPage(customerId, pageParam)));
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
