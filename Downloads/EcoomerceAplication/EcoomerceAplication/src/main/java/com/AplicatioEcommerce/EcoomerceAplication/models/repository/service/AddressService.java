package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;
import java.util.Optional;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOUpdate;



public interface AddressService {
    List<AdressDTOResponse> getAllAddresses();

    AdressDTOResponse getAddress(Long id);

    List<AdressDTOResponse> getAddressesByCustomer(Long customerId);

    AdressDTOResponse addAddressToCustomer(Long customerId, AdressDTOUpdate dto);

    AdressDTOResponse updateAddress(Long id, AdressDTOUpdate dto);

    void deleteAddress(Long id);
}
