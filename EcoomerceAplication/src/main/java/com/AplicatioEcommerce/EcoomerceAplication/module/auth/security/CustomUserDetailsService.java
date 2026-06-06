package com.AplicatioEcommerce.EcoomerceAplication.module.auth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.repository.CustomerDao;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final CustomerDao customerdao;
	
	public CustomUserDetailsService(CustomerDao customerdao) {
		this.customerdao = customerdao;
	}
	
	
	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("[loadUserByUsername] username: {}", username);

		Customer customer = customerdao.findByEmail(username)
				.orElseThrow(() -> new CustomerNotFoundException("Error al encontrar el usuario " + username));

		log.debug("[loadUserByUsername] Usuario encontrado: {}", customer.getEmail());
		
		return  new CustomUserDetails(customer);
	}
	

}
