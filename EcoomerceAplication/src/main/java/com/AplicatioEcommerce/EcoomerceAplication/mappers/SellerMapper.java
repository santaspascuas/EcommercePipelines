package com.AplicatioEcommerce.EcoomerceAplication.mappers;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.models.Seller;

public class SellerMapper {

    private SellerMapper() {}

    public static SellerResponseDTO toResponse(Seller seller) {
        return new SellerResponseDTO(
                seller.getSellerId(),
                seller.getFirstName(),
                seller.getLastName(),
                seller.getEmail(),
                seller.getMobileNumber()
        );
    }

    public static Seller toEntity(SellerRequestDTO dto) {
        Seller seller = new Seller();
        seller.setFirstName(dto.getFirstName());
        seller.setLastName(dto.getLastName());
        seller.setPassword(dto.getPassword());
        seller.setMobileNumber(dto.getMobileNumber());
        seller.setEmail(dto.getEmail());
        return seller;
    }

    public static void updateEntity(Seller seller, SellerRequestDTO dto) {
        seller.setFirstName(dto.getFirstName());
        seller.setLastName(dto.getLastName());
        seller.setPassword(dto.getPassword());
        seller.setMobileNumber(dto.getMobileNumber());
        seller.setEmail(dto.getEmail());
    }
}
