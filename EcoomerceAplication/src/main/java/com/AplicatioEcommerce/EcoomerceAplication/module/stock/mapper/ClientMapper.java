package com.AplicatioEcommerce.EcoomerceAplication.module.stock.mapper;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Client;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;

public class ClientMapper {

    private ClientMapper() {}

    public static ClientResponseDTO toResponse(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setClientId(client.getClientId());
        dto.setLegalName(client.getLegalName());
        dto.setTradeName(client.getTradeName());
        dto.setNif(client.getNif());
        dto.setAlternativeId(client.getAlternativeId());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setWeb(client.getWeb());
        dto.setContactPerson(client.getContactPerson());
        dto.setNotes(client.getNotes());
        dto.setActive(client.isActive());
        dto.setCreatedAt(client.getCreatedAt());
        dto.setUpdatedAt(client.getUpdatedAt());
        return dto;
    }

    public static Client toEntity(Customer customer, ClientRequestDTO dto) {
        Client client = new Client();
        client.setLegalName(dto.getLegalName());
        client.setTradeName(dto.getTradeName());
        client.setNif(dto.getNif());
        client.setAlternativeId(dto.getAlternativeId());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setWeb(dto.getWeb());
        client.setContactPerson(dto.getContactPerson());
        client.setNotes(dto.getNotes());
        client.setActive(true);
        client.setCustomer(customer);
        return client;
    }

    public static void updateEntity(Client client, ClientRequestDTO dto) {
        client.setLegalName(dto.getLegalName());
        client.setTradeName(dto.getTradeName());
        client.setNif(dto.getNif());
        client.setAlternativeId(dto.getAlternativeId());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setWeb(dto.getWeb());
        client.setContactPerson(dto.getContactPerson());
        client.setNotes(dto.getNotes());
    }
}
