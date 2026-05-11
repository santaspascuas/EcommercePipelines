package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerResponseDTO;

public interface SellerService {

    SellerResponseDTO anadirSeller(SellerRequestDTO dto);

    SellerResponseDTO getSellerById(Long sellerId);

    List<SellerResponseDTO> getAllSellers();

    SellerResponseDTO updateSeller(Long sellerId, SellerRequestDTO dto);

    void deleteSeller(Long sellerId);
}
