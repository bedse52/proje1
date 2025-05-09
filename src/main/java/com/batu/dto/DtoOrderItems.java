package com.batu.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderItems {

	private Long id;

	private Long productId;

	private BigDecimal price;

	private Integer quantity;

	private BigDecimal subtotal;
}
