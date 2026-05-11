package com.AplicatioEcommerce.EcoomerceAplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.models.Address;


//En el dao los optionals

public interface AddressDao extends JpaRepository<Address,Long>{
	Optional<Address> findById(Long id);
	List<Address> findListByCustomerId(Long customerId);
	Optional<Address> findByCustomerId(Long customerId);


}
