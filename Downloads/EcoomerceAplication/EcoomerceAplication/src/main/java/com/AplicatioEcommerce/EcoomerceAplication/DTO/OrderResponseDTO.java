package com.AplicatioEcommerce.EcoomerceAplication.DTO;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseDTO {

    private Long orderId;
    private LocalDate date;
    private String orderStatus;
    private Double total;
    private String customerEmail;
    private Long addressId;
    private List<OrderItemResponseDTO> items;

    public OrderResponseDTO() {}

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public List<OrderItemResponseDTO> getItems() { return items; }
    public void setItems(List<OrderItemResponseDTO> items) { this.items = items; }
}
