package com.AplicatioEcommerce.EcoomerceAplication.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name="orderStatus")
@ToString
public class OrderStatusHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderStatus_id_generator")
	@SequenceGenerator(
		name = "orderStatus_id_generator",
		sequenceName = "orderStatus_id_seq",
		allocationSize = 1
	)
	private Long orderStatusId;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatusValues orderStatus;
	
	private LocalDateTime timestamp;
	
	
	
	public OrderStatusHistory() {}
	
	

	public Long getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Long orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderStatusValues getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusValues orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	

}
