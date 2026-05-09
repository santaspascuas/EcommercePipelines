package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusValues;

public interface OrderService {

    OrderResponseDTO crearOrder(OrderRequestDTO dto);

    OrderResponseDTO getOrderById(Long orderId);

    List<OrderResponseDTO> getAllOrders();

    List<OrderResponseDTO> getOrdersByCustomer(Long customerId);

    OrderResponseDTO updateOrderStatus(Long orderId, OrderStatusValues status);

    void cancelOrder(Long orderId);
}
