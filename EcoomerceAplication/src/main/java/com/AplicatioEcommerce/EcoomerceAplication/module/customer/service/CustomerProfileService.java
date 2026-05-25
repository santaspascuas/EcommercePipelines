package com.AplicatioEcommerce.EcoomerceAplication.module.customer.service;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTOResponse;

public interface CustomerProfileService {

    CustomerProfileDTOResponse getProfile(Long customerId);

    CustomerProfileDTOResponse updateProfile(Long customerId, CustomerProfileDTO dto);
    
    CustomerProfileDTOResponse insertarProfile(Long customerId, CustomerProfileDTO dto);
    
}
