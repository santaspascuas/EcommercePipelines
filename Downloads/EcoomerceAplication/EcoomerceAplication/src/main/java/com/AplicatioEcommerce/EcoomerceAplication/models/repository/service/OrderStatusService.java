package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.util.List;

import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusHistory;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusValues;

public interface OrderStatusService {
	
	
    OrderStatusHistory addStatus(Long orderId, OrderStatusValues newStatus);

    
    List<OrderStatusHistory> getHistory(Long orderId);

    
    OrderStatusHistory getLastStatus(Long orderId);

}
