package com.AplicatioEcommerce.EcoomerceAplication.module.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.mapper.CustomerProfileMapper;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CustomerProfile;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository.CustomerProfileDao;

import jakarta.transaction.Transactional;

@Service
public class CustomerProfileServiceImplements implements CustomerProfileService {

    private static final Logger log = LoggerFactory.getLogger(CustomerProfileServiceImplements.class);

    private final CustomerProfileDao customerProfileDao;

    public CustomerProfileServiceImplements(CustomerProfileDao customerProfileDao) {
        this.customerProfileDao = customerProfileDao;
    }

    @Override
    public CustomerProfileDTOResponse getProfile(Long customerId) {
        log.info("[getProfile] customerId={}", customerId);
        CustomerProfile profile = customerProfileDao.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Perfil no encontrado para el cliente " + customerId));
        return CustomerProfileMapper.toResponse(profile);
    }

    @Override
    @Transactional
    public CustomerProfileDTOResponse updateProfile(Long customerId, CustomerProfileDTO dto) {
        log.info("[updateProfile] customerId={}", customerId);

        CustomerProfile profile = customerProfileDao.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Perfil no encontrado para el cliente " + customerId));

        if (dto.getPhone() != null && !dto.getPhone().equals(profile.getPhone())
                && customerProfileDao.existsByPhoneAndIdNot(dto.getPhone(), customerId)) {
            throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND,"Error en la actualizcion del perfil");
        }
        if (dto.getNif() != null && !dto.getNif().equals(profile.getNif())
                && customerProfileDao.existsByNifAndIdNot(dto.getNif(), customerId)) {
            throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND,"Ya existe un cliente con ese NIF/CIF");
        }

        CustomerProfileMapper.updateEntity(profile, dto);
        customerProfileDao.save(profile);

        log.info("[updateProfile] Fin OK");
        return CustomerProfileMapper.toResponse(profile);
    }

	@Override
	public CustomerProfileDTOResponse insertarProfile(Long customerId, CustomerProfileDTO dto) {
		log.info("[insertarProfile] customerId={}", customerId);
		
		if(customerProfileDao.findById(customerId).isPresent()) {
			throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND,"Ya existe un cliente con ese NIF/CIF");
		}
		
		CustomerProfile profile = CustomerProfileMapper.insertarEntidadbydto(dto);
		
		customerProfileDao.save(profile);
		log.info("[Insertar] Fin OK");
		
		// TODO Auto-generated method stub
		return CustomerProfileMapper.toResponse(profile);
	}
}
