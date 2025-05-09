package com.batu.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Base{

	private String name;
	
	@OneToOne(mappedBy = "customer")
	@JsonManagedReference
	private Cart cart;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
}
