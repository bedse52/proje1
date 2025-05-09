package com.batu.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="price_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistory extends Base{

	@ManyToOne
	private Product product;
	
	private BigDecimal price;
	
	@CreatedDate
	private LocalDateTime validFrom;
}
