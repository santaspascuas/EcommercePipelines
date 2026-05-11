package com.AplicatioEcommerce.EcoomerceAplication.mappers;

import java.time.LocalDateTime;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.models.Customer;

public class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerDTOResponse toResponse(Customer customer) {
        return new CustomerDTOResponse(
                customer.getFirstName(),
                customer.getEmail(),
                customer.getIban()
        );
    }

    public static Customer toEntity(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setIban(dto.getIban());
        customer.setAltausuario(LocalDateTime.now());
        return customer;
    }

    public static void updateEntity(Customer customer, CustomerRequestDTO dto) {
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setIban(dto.getIban());
    }
}
