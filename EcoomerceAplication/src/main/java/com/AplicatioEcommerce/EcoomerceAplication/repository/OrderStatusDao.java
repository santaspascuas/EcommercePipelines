package com.AplicatioEcommerce.EcoomerceAplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AplicatioEcommerce.EcoomerceAplication.models.OrderStatusHistory;

public interface OrderStatusDao extends JpaRepository<OrderStatusHistory,Long>{
	
	Optional<OrderStatusHistory> findById(Long id);
	
	List<OrderStatusHistory> findByOrderOrderIdOrderByTimestampAsc(Long orderId);
	
	List<OrderStatusHistory> findByOrderOrderIdOrderByTimestampDesc(Long orderId);
	
	OrderStatusHistory findTopByOrderOrderIdOrderByTimestampDesc(Long orderId);

	
	

}
