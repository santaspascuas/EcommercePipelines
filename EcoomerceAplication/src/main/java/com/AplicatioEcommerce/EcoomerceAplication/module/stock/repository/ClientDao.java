package com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);

    boolean existsByNif(String nif);
}
