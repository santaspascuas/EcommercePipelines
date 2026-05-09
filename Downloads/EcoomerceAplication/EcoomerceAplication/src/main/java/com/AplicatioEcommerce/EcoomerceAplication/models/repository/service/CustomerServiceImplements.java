package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.exception.CustomerException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.mappers.CustomerMapper;
import com.AplicatioEcommerce.EcoomerceAplication.models.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.repository.CustomerDao;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImplements implements CustomerService {

    @Autowired
    private CustomerDao customerdao;

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImplements.class);

    @Override
    @Transactional
    public CustomerDTOResponse anadirCustomer(CustomerRequestDTO dto) {
        log.info("[anadirCustomer] Inicio");

        if (customerdao.existsByEmail(dto.getEmail())) {
            throw new CustomerException("Ya existe un customer con ese email");
        }
        if (customerdao.existsBymobileNumber(dto.getMobileNumber())) {
            throw new CustomerException("Ya existe un customer con ese telefono");
        }
        if (customerdao.existsByIban(dto.getIban())) {
            throw new CustomerException("Ya existe un customer con ese IBAN");
        }
        log.debug("Los datos del frontal:{}", dto.toString());
        
        Customer customer = CustomerMapper.toEntity(dto);
        log.debug("Customer a insertar: {}", customer.toString());
        //iniciamoslas relaciones
        customer.setAddresses(new ArrayList<>());
        customer.setOrders(new ArrayList<>());
        log.debug("Insertar customer tras iniciazion:{}", customer.toString());

        Customer saved = customerdao.save(customer);

        log.info("[anadirCustomer] Fin OK");
        return CustomerMapper.toResponse(saved);
    }

    @Override
    public List<CustomerDTOResponse> getallCustomer() {
        log.info("[getallCustomer] Inicio");
        List<CustomerDTOResponse> result = customerdao.findAll().stream()
                .map(CustomerMapper::toResponse)
                .toList();
        log.info("[getallCustomer] Fin OK - {} customers", result.size());
        return result;
    }

    @Override
    @Transactional
    public CustomerDTOResponse updateCustomer(Long idCustomer, CustomerRequestDTO dto) {
        log.info("[updateCustomer] Inicio - id {}", idCustomer);

        Customer customer = customerdao.findById(idCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No existe el customer con id " + idCustomer));

        if (!customer.getEmail().equals(dto.getEmail()) && customerdao.existsByEmail(dto.getEmail())) {
            throw new CustomerException("Ya existe un customer con ese email");
        }
        if (!customer.getMobileNumber().equals(dto.getMobileNumber()) && customerdao.existsBymobileNumber(dto.getMobileNumber())) {
            throw new CustomerException("Ya existe un customer con ese telefono");
        }
        if (!customer.getIban().equals(dto.getIban()) && customerdao.existsByIban(dto.getIban())) {
            throw new CustomerException("Ya existe un customer con ese IBAN");
        }

        CustomerMapper.updateEntity(customer, dto);
        customerdao.save(customer);

        log.info("[updateCustomer] Fin OK");
        return CustomerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long idCustomer) {
        log.info("[deleteCustomer] Inicio - id {}", idCustomer);

        if (!customerdao.existsById(idCustomer)) {
            throw new CustomerNotFoundException("No existe el customer con id " + idCustomer);
        }

        customerdao.deleteById(idCustomer);
        log.info("[deleteCustomer] Customer {} eliminado", idCustomer);
    }
}
