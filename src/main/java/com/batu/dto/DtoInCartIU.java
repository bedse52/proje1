package com.batu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoInCartIU {

	private Long productId;
	
	private Integer quantity;
}
