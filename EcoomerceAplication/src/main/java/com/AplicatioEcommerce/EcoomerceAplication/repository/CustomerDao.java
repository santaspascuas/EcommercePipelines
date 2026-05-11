package com.AplicatioEcommerce.EcoomerceAplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.models.Customer;

public interface CustomerDao extends JpaRepository<Customer,Long>{
	//creo las funciones necesarias
	Optional<Customer>findByEmail(String email);
	boolean existsByIban(String iban);
	boolean existsBymobileNumber(String mobileNumber);
	boolean existsByEmail(String email);
}
