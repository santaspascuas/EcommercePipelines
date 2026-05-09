package com.AplicatioEcommerce.EcoomerceAplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.models.Order;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusValues;

public interface OrderDao extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByOrderStatus(OrderStatusValues status);
}
