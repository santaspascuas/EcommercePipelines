package com.AplicatioEcommerce.EcoomerceAplication.module.stock.mapper;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Address;

public class AddressMapper {

    private AddressMapper() {}

    public static AdressDTOResponse toResponse(Address address) {
        return new AdressDTOResponse(
                address.getAddressId(),
                address.getStreet(),
                address.getNumber(),
                address.getFloor(),
                address.getCity(),
                address.getProvince(),
                address.getPostalCode(),
                address.getCountry()
        );
    }

    public static Address toEntity(AdressDTOUpdate dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setFloor(dto.getFloor());
        address.setCity(dto.getCity());
        address.setProvince(dto.getProvince());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        return address;
    }

    public static void updateEntity(Address address, AdressDTOUpdate dto) {
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setFloor(dto.getFloor());
        address.setCity(dto.getCity());
        address.setProvince(dto.getProvince());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
    }
}
