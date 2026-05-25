package com.AplicatioEcommerce.EcoomerceAplication.module.customer.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerAuthUpdateDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;

public interface CustomerService {

    CustomerDTOResponse anadirCustomer(CustomerRequestDTO dto);

    List<CustomerDTOResponse> getallCustomer();

    PageResult<CustomerDTOResponse> getCustomerPage(PageParam pageParam);

    CustomerDTOResponse updateCustomer(Long idCustomer, CustomerAuthUpdateDTO dto);

    void deleteCustomer(Long idCustomer);

    Customer encuentra(String email);
    
    CustomerDTOResponse registerCustomer(CustomerRequestDTO dto);
    
    Customer guardaLoguinCustomer(Customer customer);
    
    
}
