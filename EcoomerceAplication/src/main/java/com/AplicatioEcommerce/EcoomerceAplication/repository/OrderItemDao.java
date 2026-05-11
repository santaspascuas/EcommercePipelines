package com.AplicatioEcommerce.EcoomerceAplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.models.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderOrderId(Long orderId);
}
