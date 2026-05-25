package com.AplicatioEcommerce.EcoomerceAplication.module.customer.mapper;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;

public class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerDTOResponse toResponse(Customer customer) {
        CustomerDTOResponse dto = new CustomerDTOResponse();
        dto.setId(customer.getId());
        dto.setEmail(customer.getEmail());
        dto.setRole(customer.getRole());
        dto.setActive(customer.isActive());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        dto.setProfile(CustomerProfileMapper.toResponse(customer.getProfile()));
        return dto;
    }

    public static Customer toEntity(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setRole("ROLE_CUSTOMER");
        customer.setActive(true);
        return customer;
    }
}
