package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;

public interface ClientService {

    ClientResponseDTO addClient(ClientRequestDTO dto);

    ClientResponseDTO getClientById(Long clientId);

    List<ClientResponseDTO> getClientsByCustomer();

    PageResult<ClientResponseDTO> getClientsPage(PageParam pageParam);

    ClientResponseDTO updateClient(Long clientId, ClientRequestDTO dto);

    void deleteClient(Long clientId);
}
