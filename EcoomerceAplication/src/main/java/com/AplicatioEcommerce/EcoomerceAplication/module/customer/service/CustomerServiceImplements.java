package com.AplicatioEcommerce.EcoomerceAplication.module.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerAuthUpdateDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.mapper.CustomerMapper;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository.CustomerDao;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImplements implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImplements.class);

    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImplements(CustomerDao customerDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override 
    @Transactional
    public CustomerDTOResponse anadirCustomer(CustomerRequestDTO dto) {
        log.info("[anadirCustomer] Inicio");

        if (customerDao.existsByEmail(dto.getEmail())) {
            throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_EMAIL_DUPLICATE);
        }

        Customer customer = CustomerMapper.toEntity(dto);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        Customer saved = customerDao.save(customer);

        log.info("[anadirCustomer] Fin OK - id {}", saved.getId());
        return CustomerMapper.toResponse(saved);
    }

    @Override
    public List<CustomerDTOResponse> getallCustomer() {
        log.info("[getallCustomer] Inicio");
        List<CustomerDTOResponse> result = customerDao.findAll().stream()
                .map(CustomerMapper::toResponse)
                .toList();
        log.info("[getallCustomer] Fin OK - {} clientes", result.size());
        return result;
    }

    @Override
    @Transactional
    public CustomerDTOResponse updateCustomer(Long idCustomer, CustomerAuthUpdateDTO dto) {
        log.info("[updateCustomer] Inicio - id {}", idCustomer);

        Customer customer = customerDao.findById(idCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No existe el cliente con id " + idCustomer));

        if (dto.getEmail() != null && !dto.getEmail().equals(customer.getEmail())) {
            if (customerDao.existsByEmail(dto.getEmail())) {
                throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_EMAIL_DUPLICATE);
            }
            customer.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        customerDao.save(customer);
        log.info("[updateCustomer] Fin OK");
        return CustomerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long idCustomer) {
        log.info("[deleteCustomer] Inicio - id {}", idCustomer);

        if (!customerDao.existsById(idCustomer)) {
            throw new CustomerNotFoundException("No existe el cliente con id " + idCustomer);
        }

        customerDao.deleteById(idCustomer);
        log.info("[deleteCustomer] Cliente {} eliminado", idCustomer);
    }

    @Override
    @Transactional
    public Customer encuentra(String email) {
        log.info("[encuentra] email {}", email);
        return customerDao.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("No existe el cliente con ese email"));
    }

    @Override
    @Transactional
    public CustomerDTOResponse registerCustomer(CustomerRequestDTO dto) {
        log.info("[registerCustomer] Inicio");

        if (customerDao.existsByEmail(dto.getEmail())) {
            throw new CustomerException(GlobalErrorCodeConstants.CUSTOMER_EMAIL_DUPLICATE);
        }

        Customer customer = CustomerMapper.toEntity(dto);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        Customer saved = customerDao.save(customer);

        log.info("[registerCustomer] Fin OK - id {}", saved.getId());
        return CustomerMapper.toResponse(saved);
    }

    @Override
    public Customer guardaLoguinCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public PageResult<CustomerDTOResponse> getCustomerPage(PageParam pageParam) {
        log.info("[getCustomerPage] pageNo={}, pageSize={}", pageParam.getPageNo(), pageParam.getPageSize());
        return new PageResult<>(
                customerDao.findAll(pageParam.toPageable(Sort.by("id").ascending()))
                        .map(CustomerMapper::toResponse)
        );
    }
    
    
    
    
    
}
