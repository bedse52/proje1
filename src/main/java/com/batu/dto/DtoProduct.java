package com.batu.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProduct {

	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private Integer stock;
}
