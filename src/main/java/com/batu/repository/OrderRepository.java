package com.batu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batu.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Optional<Order> findTopByOrderByCreatedAtDesc();
	
	Optional<Order> findByOrderCode(String orderCode);
	
	List<Order> findByCustomerId(Long customerId);
}
