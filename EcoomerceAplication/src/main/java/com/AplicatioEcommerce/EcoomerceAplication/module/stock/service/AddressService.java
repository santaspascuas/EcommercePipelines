package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;

public interface AddressService {
    List<AdressDTOResponse> getAllAddresses();

    PageResult<AdressDTOResponse> getAllAddressesPage(PageParam pageParam);

    AdressDTOResponse getAddress(Long id);

    List<AdressDTOResponse> getAddressesByCustomer(Long customerId);

    PageResult<AdressDTOResponse> getAddressesPage(Long customerId, PageParam pageParam);

    AdressDTOResponse addAddressToCustomer(Long customerId, AdressDTOUpdate dto);

    AdressDTOResponse updateAddress(Long id, AdressDTOUpdate dto);

    void deleteAddress(Long id);
}
