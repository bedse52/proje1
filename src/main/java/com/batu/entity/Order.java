package com.batu.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Base{

	@ManyToOne
	private Customer customer;
	
	private String orderCode;
	
	private BigDecimal totalPrice;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItems> orderItems;
	
}
