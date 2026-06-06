package com.AplicatioEcommerce.EcoomerceAplication.module.customer.mapper;

import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerProfileDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CustomerProfile;

import java.util.ArrayList;

public class CustomerProfileMapper {

    private CustomerProfileMapper() {}

    public static CustomerProfileDTOResponse toResponse(CustomerProfile profile) {
        if (profile == null) return null;
        CustomerProfileDTOResponse dto = new CustomerProfileDTOResponse();
        dto.setLegalName(profile.getLegalName());
        dto.setTradeName(profile.getTradeName());
        dto.setNif(profile.getNif());
        dto.setAlternativeId(profile.getAlternativeId());
        dto.setPhone(profile.getPhone());
        dto.setWeb(profile.getWeb());
        dto.setBillingEmails(profile.getBillingEmails());
        dto.setContactPerson(profile.getContactPerson());
        dto.setNotes(profile.getNotes());
        dto.setPreferredPaymentMethod(profile.getPreferredPaymentMethod());
        dto.setGeneralDiscount(profile.getGeneralDiscount());
        return dto;
    }

    public static CustomerProfile toEntity(Customer customer, String legalName) {
        CustomerProfile profile = new CustomerProfile();
        profile.setCustomer(customer);
        profile.setLegalName(legalName);
        profile.setBillingEmails(new ArrayList<>());
        return profile;
    }

    public static void updateEntity(CustomerProfile profile, CustomerProfileDTO dto) {
        profile.setLegalName(dto.getLegalName());
        profile.setTradeName(dto.getTradeName());
        profile.setNif(dto.getNif());
        profile.setAlternativeId(dto.getAlternativeId());
        profile.setPhone(dto.getPhone());
        profile.setWeb(dto.getWeb());
        if (dto.getBillingEmails() != null) profile.setBillingEmails(dto.getBillingEmails());
        profile.setContactPerson(dto.getContactPerson());
        profile.setNotes(dto.getNotes());
        profile.setPreferredPaymentMethod(dto.getPreferredPaymentMethod());
        profile.setGeneralDiscount(dto.getGeneralDiscount());
    }
    
    public static CustomerProfile insertarEntidadbydto(CustomerProfileDTO dto) {
    	
    	CustomerProfile profile = new CustomerProfile();
    	profile.setLegalName(dto.getLegalName());
    	profile.setTradeName(dto.getTradeName());
        profile.setNif(dto.getNif());
        profile.setAlternativeId(dto.getAlternativeId());
        profile.setPhone(dto.getPhone());
        profile.setWeb(dto.getWeb());
        if (dto.getBillingEmails() != null) profile.setBillingEmails(dto.getBillingEmails());
        profile.setContactPerson(dto.getContactPerson());
        profile.setNotes(dto.getNotes());
        profile.setPreferredPaymentMethod(dto.getPreferredPaymentMethod());
        profile.setGeneralDiscount(dto.getGeneralDiscount());
        return profile;
    	
    }
    
    
}
