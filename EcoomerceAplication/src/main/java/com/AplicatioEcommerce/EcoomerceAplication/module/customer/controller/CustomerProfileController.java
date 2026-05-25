package com.AplicatioEcommerce.EcoomerceAplication.module.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.service.AddressServiceImplements;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.service.CustomerProfileService;
import com.AplicatioEcommerce.EcoomerceAplication.module.auth.security.CustomUserDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers/profile")
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;
    private static final Logger log = LoggerFactory.getLogger(CustomerProfileController.class);

    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerProfileDTOResponse>> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
    	Customer customer = userDetails.getCustomer();
        CustomerProfileDTOResponse profile = customerProfileService.getProfile(customer.getId());
        return ResponseEntity.ok(ResponseUtil.success("Perfil del cliente", profile));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CustomerProfileDTOResponse>> updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,@Valid @RequestBody CustomerProfileDTO dto) {
    	Long id = userDetails.getCustomer().getId();
    	log.info("ResponseEntity<ApiResponse<CustomerProfileDTOResponse>> updateProfile--inicio");
    	log.debug("Long id", id);
    	
    	CustomerProfileDTOResponse updated = customerProfileService.updateProfile(id, dto);
        return ResponseEntity.ok(ResponseUtil.success("Perfil actualizado correctamente", updated));
    }
    
    
    
}
