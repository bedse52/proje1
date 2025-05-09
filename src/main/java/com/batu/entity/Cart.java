package com.batu.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends Base{

	@OneToOne
	@JsonBackReference
	private Customer customer;
	
	private BigDecimal totalPrice;
	
	@OneToMany(mappedBy = "cart",orphanRemoval = true)
	private List<InCart> cartItems;
}
