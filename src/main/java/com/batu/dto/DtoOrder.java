package com.batu.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrder {
 
	private Long id;
	
	private Long customer;
	
	private String orderCode;
	
	private BigDecimal totalPrice;
	
	private List<DtoOrderItems> orderItems = new ArrayList<>();
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime createdAt; 
	
}
