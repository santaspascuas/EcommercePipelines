package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto.ClientResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageParam;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.PageResult;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ClientException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.ClientNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.mapper.ClientMapper;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Client;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.ClientDao;

import jakarta.transaction.Transactional;

@Service
public class ClientServiceImplements implements ClientService {

    private final ClientDao clientDao;
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImplements.class);

    public ClientServiceImplements(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public ClientResponseDTO addClient(ClientRequestDTO dto) {
        log.info("[addClient] Inicio");

        if (dto.getEmail() != null && clientDao.existsByEmail(dto.getEmail())) {
            throw new ClientException(GlobalErrorCodeConstants.CLIENT_EMAIL_DUPLICATE);
        }
        if (dto.getNif() != null && clientDao.existsByNif(dto.getNif())) {
            throw new ClientException(GlobalErrorCodeConstants.CLIENT_NIF_DUPLICATE);
        }

        Client saved = clientDao.save(ClientMapper.toEntity(dto));
        log.info("[addClient] Fin OK - clientId={}", saved.getClientId());
        return ClientMapper.toResponse(saved);
    }

    @Override
    public ClientResponseDTO getClientById(Long clientId) {
        log.info("[getClientById] id={}", clientId);
        Client client = clientDao.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("id: " + clientId));
        return ClientMapper.toResponse(client);
    }

    @Override
    public List<ClientResponseDTO> getClientsByCustomer() {
        log.info("[getClientsByCustomer] Inicio");
        return clientDao.findAll().stream()
                .map(ClientMapper::toResponse)
                .toList();
    }

    @Override
    public PageResult<ClientResponseDTO> getClientsPage(PageParam pageParam) {
        log.info("[getClientsPage] Inicio");
        return new PageResult<>(
                clientDao.findAll(pageParam.toPageable(Sort.by("legalName").ascending()))
                        .map(ClientMapper::toResponse)
        );
    }

    @Override
    @Transactional
    public ClientResponseDTO updateClient(Long clientId, ClientRequestDTO dto) {
        log.info("[updateClient] id={}", clientId);

        Client client = clientDao.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("id: " + clientId));

        if (dto.getEmail() != null && !dto.getEmail().equals(client.getEmail())
                && clientDao.existsByEmail(dto.getEmail())) {
            throw new ClientException(GlobalErrorCodeConstants.CLIENT_EMAIL_DUPLICATE);
        }
        if (dto.getNif() != null && !dto.getNif().equals(client.getNif())
                && clientDao.existsByNif(dto.getNif())) {
            throw new ClientException(GlobalErrorCodeConstants.CLIENT_NIF_DUPLICATE);
        }

        ClientMapper.updateEntity(client, dto);
        clientDao.save(client);
        log.info("[updateClient] Fin OK");
        return ClientMapper.toResponse(client);
    }

    @Override
    @Transactional
    public void deleteClient(Long clientId) {
        log.info("[deleteClient] id={}", clientId);
        if (!clientDao.existsById(clientId)) {
            throw new ClientNotFoundException("id: " + clientId);
        }
        clientDao.deleteById(clientId);
        log.info("[deleteClient] Cliente {} eliminado", clientId);
    }
}
