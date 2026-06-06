package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.AdressNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ServiceException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.security.TenantContextHolder;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.mapper.AddressMapper;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Address;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.AddressDao;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository.CustomerDao;

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImplements implements AddressService {

  
    private AddressDao addresdao;
    private CustomerDao customerdao;
    
    public AddressServiceImplements(AddressDao addresdao, CustomerDao customerdao) {
		super();
		this.addresdao = addresdao;
		this.customerdao = customerdao;
	}

	private static final Logger log = LoggerFactory.getLogger(AddressServiceImplements.class);

    @Override
    public List<AdressDTOResponse> getAllAddresses() {
        log.info("[getAllAddresses] Inicio");
        return addresdao.findAll().stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    public AdressDTOResponse getAddress(Long id) {
        log.info("[getAddress] Inicio - id {}", id);
        Address address = addresdao.findById(id)
                .orElseThrow(() -> new AdressNotFoundException("La localizacion con id " + id + " no existe"));
        checkOwnership(address.getCustomer().getId());
        return AddressMapper.toResponse(address);
    }

    @Override
    public List<AdressDTOResponse> getAddressesByCustomer(Long customerId) {
        log.info("[getAddressesByCustomer] Inicio - customerId {}", customerId);
        return addresdao.findListByCustomerId(customerId).stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AdressDTOResponse addAddressToCustomer(Long customerId, AdressDTOUpdate dto) {
        log.info("[addAddressToCustomer] Inicio - customerId {}", customerId);

        Customer customer = customerdao.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("No existe el customer con id " + customerId));
        log.debug("Obtenemos el customer:{}",customer.toString());
        
        Address address = AddressMapper.toEntity(dto);
        address.setCustomer(customer);
        

        Address saved = addresdao.save(address);

        log.info("[addAddressToCustomer] Fin OK - addressId {}", saved.getAddressId());
        return AddressMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AdressDTOResponse updateAddress(Long id, AdressDTOUpdate dto) {
        log.info("[updateAddress] Inicio - id {}", id);

        Address address = addresdao.findById(id)
                .orElseThrow(() -> new AdressNotFoundException("La localizacion con id " + id + " no existe"));
        checkOwnership(address.getCustomer().getId());

        AddressMapper.updateEntity(address, dto);
        addresdao.save(address);

        log.info("[updateAddress] Fin OK");
        return AddressMapper.toResponse(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        log.info("[deleteAddress] Inicio - id {}", id);

        Address address = addresdao.findById(id)
                .orElseThrow(() -> new AdressNotFoundException("La localizacion con id " + id + " no existe"));
        checkOwnership(address.getCustomer().getId());

        addresdao.deleteById(id);
        log.info("[deleteAddress] Direccion {} eliminada", id);
    }

    private void checkOwnership(Long ownerCustomerId) {
        Long tenantId = TenantContextHolder.getCustomerId();
        if (tenantId != null && !tenantId.equals(ownerCustomerId)) {
            throw new ServiceException(GlobalErrorCodeConstants.FORBIDDEN);
        }
    }

    @Override
    public PageResult<AdressDTOResponse> getAllAddressesPage(PageParam pageParam) {
        log.info("[getAllAddressesPage] Inicio");
        return new PageResult<>(
                addresdao.findAll(pageParam.toPageable(Sort.by("city").ascending()))
                        .map(AddressMapper::toResponse)
        );
    }

    @Override
    public PageResult<AdressDTOResponse> getAddressesPage(Long customerId, PageParam pageParam) {
        log.info("[getAddressesPage] customerId={}", customerId);
        return new PageResult<>(
                addresdao.findByCustomerId(customerId, pageParam.toPageable(Sort.by("city").ascending()))
                        .map(AddressMapper::toResponse)
        );
    }
}
