package com.AplicatioEcommerce.EcoomerceAplication.mappers;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderItemResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.models.Order;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderItem;

public class OrderMapper {

    private OrderMapper() {}

    public static OrderResponseDTO toResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setDate(order.getDate());
        dto.setOrderStatus(order.getOrderStatus() != null ? order.getOrderStatus().name() : null);
        dto.setTotal(order.getTotal());
        dto.setCustomerEmail(order.getCustomer() != null ? order.getCustomer().getEmail() : null);
        dto.setAddressId(order.getAdress() != null ? order.getAdress().getAddressId() : null);

        if (order.getItems() != null) {
            List<OrderItemResponseDTO> items = order.getItems().stream()
                    .map(OrderMapper::toItemResponse)
                    .toList();
            dto.setItems(items);
        }

        return dto;
    }

    private static OrderItemResponseDTO toItemResponse(OrderItem item) {
        return new OrderItemResponseDTO(
                item.getOrderItemId(),
                item.getProduct().getId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                item.getPriceAtPurchase()
        );
    }
}
