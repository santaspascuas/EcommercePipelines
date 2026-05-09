package com.AplicatioEcommerce.EcoomerceAplication.mappers;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.models.Address;

public class AddressMapper {

    private AddressMapper() {}

    public static AdressDTOResponse toResponse(Address address) {
        return new AdressDTOResponse(
                address.getAddressId(),
                address.getCalle(),
                address.getLocalidad(),
                address.getProvincia(),
                address.getCodigoPostal(),
                address.getPais()
        );
    }

    public static Address toEntity(AdressDTOUpdate dto) {
        Address address = new Address();
        address.setCalle(dto.getCalle());
        address.setLocalidad(dto.getLocalidad());
        address.setProvincia(dto.getProvincia());
        address.setCodigoPostal(dto.getCodigoPostal());
        address.setPais(dto.getPais());
        return address;
    }

    public static void updateEntity(Address address, AdressDTOUpdate dto) {
        address.setCalle(dto.getCalle());
        address.setLocalidad(dto.getLocalidad());
        address.setProvincia(dto.getProvincia());
        address.setCodigoPostal(dto.getCodigoPostal());
        address.setPais(dto.getPais());
    }
}
