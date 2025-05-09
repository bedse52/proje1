package com.batu.service;

import java.util.List;

import com.batu.dto.DtoOrder;

public interface IOrderService {

	public DtoOrder placeOrder(Long id);
	
	public DtoOrder getOrderByCode(String code);
	
	public List<DtoOrder> getOrdersByCustomer(Long id);
}
