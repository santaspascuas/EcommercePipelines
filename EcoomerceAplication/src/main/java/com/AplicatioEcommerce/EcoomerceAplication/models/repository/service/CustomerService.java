package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.exception.CustomerException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.models.Customer;

public interface CustomerService {
	
	//	Creamos customer	
	public CustomerDTOResponse anadirCustomer(CustomerRequestDTO customer);
	public List<CustomerDTOResponse> getallCustomer();
	void deleteCustomer(Long idCustomer);
	//actualizamos
	public CustomerDTOResponse updateCustomer(Long idCustomer,CustomerRequestDTO customer);
	

}
