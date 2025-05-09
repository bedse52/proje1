package com.batu.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table (name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Base{

	private String name;
	
	private BigDecimal price;
	
	private Integer stock;
	
	@OneToMany(mappedBy = "product", orphanRemoval = true)
	private List<PriceHistory> priceHistory;
	
}
