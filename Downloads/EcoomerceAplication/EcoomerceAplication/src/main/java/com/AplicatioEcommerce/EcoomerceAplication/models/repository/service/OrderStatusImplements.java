package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AplicatioEcommerce.EcoomerceAplication.exception.OrderNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.OrderStatusException;
import com.AplicatioEcommerce.EcoomerceAplication.models.Order;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusHistory;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusValues;
import com.AplicatioEcommerce.EcoomerceAplication.repository.OrderDao;
import com.AplicatioEcommerce.EcoomerceAplication.repository.OrderStatusDao;

@Service
public class OrderStatusImplements implements OrderStatusService {

    private final OrderStatusDao statusdao;
    private final OrderDao orderdao;

    private static final Logger log = LoggerFactory.getLogger(OrderStatusImplements.class);

    public OrderStatusImplements(OrderStatusDao statusdao, OrderDao orderdao) {
        this.statusdao = statusdao;
        this.orderdao = orderdao;
    }

    @Override
    @Transactional
    public OrderStatusHistory addStatus(Long orderId, OrderStatusValues newStatus) {
        log.info("[addStatus] orderId={}, newStatus={}", orderId, newStatus);
        Order order = orderdao.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No existe la orden con id " + orderId));
        OrderStatusHistory history = new OrderStatusHistory();
        history.setOrder(order);
        history.setOrderStatus(newStatus);
        history.setTimestamp(LocalDateTime.now());
        return statusdao.save(history);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderStatusHistory> getHistory(Long orderId) {
        log.info("[getHistory] orderId={}", orderId);
        if (!orderdao.existsById(orderId)) {
            throw new OrderNotFoundException("No existe la orden con id " + orderId);
        }
        return statusdao.findByOrderOrderIdOrderByTimestampAsc(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderStatusHistory getLastStatus(Long orderId) {
        log.info("[getLastStatus] orderId={}", orderId);
        if (!orderdao.existsById(orderId)) {
            throw new OrderNotFoundException("No existe la orden con id " + orderId);
        }
        OrderStatusHistory last = statusdao.findTopByOrderOrderIdOrderByTimestampDesc(orderId);
        if (last == null) {
            throw new OrderStatusException("No hay historial de estados para la orden con id " + orderId);
        }
        return last;
    }
}
