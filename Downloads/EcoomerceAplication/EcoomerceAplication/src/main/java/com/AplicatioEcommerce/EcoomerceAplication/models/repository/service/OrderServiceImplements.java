package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderItemRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.OrderResponseDTO;
import com.AplicatioEcommerce.EcoomerceAplication.exception.AdressNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.CustomerNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.OrderException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.OrderNotFoundException;
import com.AplicatioEcommerce.EcoomerceAplication.exception.ProductException;
import com.AplicatioEcommerce.EcoomerceAplication.mappers.OrderMapper;
import com.AplicatioEcommerce.EcoomerceAplication.models.Address;
import com.AplicatioEcommerce.EcoomerceAplication.models.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.models.Order;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderItem;
import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusValues;
import com.AplicatioEcommerce.EcoomerceAplication.models.Product;
import com.AplicatioEcommerce.EcoomerceAplication.repository.AddressDao;
import com.AplicatioEcommerce.EcoomerceAplication.repository.CustomerDao;
import com.AplicatioEcommerce.EcoomerceAplication.repository.OrderDao;
import com.AplicatioEcommerce.EcoomerceAplication.repository.ProductDao;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImplements implements OrderService {

    @Autowired
    private OrderDao orderdao;

    @Autowired
    private CustomerDao customerdao;

    @Autowired
    private AddressDao addressdao;

    @Autowired
    private ProductDao productdao;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private StockService stockService;

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImplements.class);

    @Override
    @Transactional
    public OrderResponseDTO crearOrder(OrderRequestDTO dto) {
        log.info("[crearOrder] Inicio");

        Customer customer = customerdao.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("No existe el customer con id " + dto.getCustomerId()));

        Address address = addressdao.findById(dto.getAddressId())
                .orElseThrow(() -> new AdressNotFoundException("No existe la direccion con id " + dto.getAddressId()));

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setOrderStatus(OrderStatusValues.CREATED);
        order.setCustomer(customer);
        order.setAdress(address);
        order.setCardNumber(dto.getCardNumber());

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDTO itemDto : dto.getItems()) {
            Product product = productdao.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ProductException("No existe el producto con id " + itemDto.getProductId()));

            stockService.reserveStock(product.getId(), itemDto.getQuantity());

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPriceAtPurchase(product.getPrice());
            items.add(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
        }

        order.setItems(items);
        order.setTotal(total.doubleValue());

        Order saved = orderdao.save(order);
        orderStatusService.addStatus(saved.getOrderId(), OrderStatusValues.CREATED);

        log.info("[crearOrder] Fin OK - orderId={}", saved.getOrderId());
        return OrderMapper.toResponse(saved);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        log.info("[getOrderById] id={}", orderId);
        Order order = orderdao.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No existe la orden con id " + orderId));
        return OrderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        log.info("[getAllOrders] Inicio");
        return orderdao.findAll().stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    public List<OrderResponseDTO> getOrdersByCustomer(Long customerId) {
        log.info("[getOrdersByCustomer] customerId={}", customerId);
        if (!customerdao.existsById(customerId)) {
            throw new CustomerNotFoundException("No existe el customer con id " + customerId);
        }
        return orderdao.findByCustomerId(customerId).stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatusValues status) {
        log.info("[updateOrderStatus] orderId={}, status={}", orderId, status);

        Order order = orderdao.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No existe la orden con id " + orderId));

        if (order.getOrderStatus() == OrderStatusValues.CANCELLED) {
            throw new OrderException("No se puede modificar una orden cancelada");
        }

        order.setOrderStatus(status);
        orderdao.save(order);
        orderStatusService.addStatus(orderId, status);

        log.info("[updateOrderStatus] Fin OK");
        return OrderMapper.toResponse(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        log.info("[cancelOrder] orderId={}", orderId);

        Order order = orderdao.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No existe la orden con id " + orderId));

        if (order.getOrderStatus() == OrderStatusValues.SHIPPED) {
            throw new OrderException("No se puede cancelar una orden que ya ha sido enviada");
        }

        for (OrderItem item : order.getItems()) {
            stockService.releaseStock(item.getProduct().getId(), item.getQuantity());
        }

        order.setOrderStatus(OrderStatusValues.CANCELLED);
        orderdao.save(order);
        orderStatusService.addStatus(orderId, OrderStatusValues.CANCELLED);

        log.info("[cancelOrder] Fin OK");
    }
}
