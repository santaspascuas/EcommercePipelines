package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;

public interface ClientService {

    ClientResponseDTO addClient(Long customerId, ClientRequestDTO dto);

    ClientResponseDTO getClientById(Long clientId);

    List<ClientResponseDTO> getClientsByCustomer(Long customerId);

    PageResult<ClientResponseDTO> getClientsPage(Long customerId, PageParam pageParam);

    ClientResponseDTO updateClient(Long clientId, ClientRequestDTO dto);

    void deleteClient(Long clientId);
}
