package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.SellerResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.exception.SellerException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.SellerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.mappers.SellerMapper;
import com.AplicatioEcommerce.EcoomerceAplication.models.Seller;
import com.AplicatioEcommerce.EcoomerceAplication.repository.SellerDao;

import jakarta.transaction.Transactional;

@Service
public class SellerServiceImplements implements SellerService {

    @Autowired
    private SellerDao sellerdao;

    private static final Logger log = LoggerFactory.getLogger(SellerServiceImplements.class);

    @Override
    @Transactional
    public SellerResponseDTO anadirSeller(SellerRequestDTO dto) {
        log.info("[anadirSeller] Inicio");

        if (sellerdao.existsByEmail(dto.getEmail())) {
            throw new SellerException("Ya existe un seller con ese email");
        }
        if (sellerdao.existsByMobileNumber(dto.getMobileNumber())) {
            throw new SellerException("Ya existe un seller con ese telefono");
        }

        Seller saved = sellerdao.save(SellerMapper.toEntity(dto));

        log.info("[anadirSeller] Fin OK - sellerId {}", saved.getSellerId());
        return SellerMapper.toResponse(saved);
    }

    @Override
    public SellerResponseDTO getSellerById(Long sellerId) {
        log.info("[getSellerById] Inicio - id {}", sellerId);
        Seller seller = sellerdao.findById(sellerId)
                .orElseThrow(() -> new SellerNotFoundException("No existe el seller con id " + sellerId));
        return SellerMapper.toResponse(seller);
    }

    @Override
    public List<SellerResponseDTO> getAllSellers() {
        log.info("[getAllSellers] Inicio");
        return sellerdao.findAll().stream()
                .map(SellerMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public SellerResponseDTO updateSeller(Long sellerId, SellerRequestDTO dto) {
        log.info("[updateSeller] Inicio - id {}", sellerId);

        Seller seller = sellerdao.findById(sellerId)
                .orElseThrow(() -> new SellerNotFoundException("No existe el seller con id " + sellerId));

        if (!seller.getEmail().equals(dto.getEmail()) && sellerdao.existsByEmail(dto.getEmail())) {
            throw new SellerException("Ya existe un seller con ese email");
        }
        if (!seller.getMobileNumber().equals(dto.getMobileNumber()) && sellerdao.existsByMobileNumber(dto.getMobileNumber())) {
            throw new SellerException("Ya existe un seller con ese telefono");
        }

        SellerMapper.updateEntity(seller, dto);
        sellerdao.save(seller);

        log.info("[updateSeller] Fin OK");
        return SellerMapper.toResponse(seller);
    }

    @Override
    @Transactional
    public void deleteSeller(Long sellerId) {
        log.info("[deleteSeller] Inicio - id {}", sellerId);

        if (!sellerdao.existsById(sellerId)) {
            throw new SellerNotFoundException("No existe el seller con id " + sellerId);
        }

        sellerdao.deleteById(sellerId);
        log.info("[deleteSeller] Seller {} eliminado", sellerId);
    }
}
