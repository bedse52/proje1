package com.batu.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCart {

	private Long id;
	
	private BigDecimal totalPrice;
	
	private List<DtoInCart> inCart= new ArrayList<>();
	
	

}
