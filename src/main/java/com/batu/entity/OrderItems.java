package com.batu.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems extends Base{

	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Product product;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	private BigDecimal subtotal;
}
