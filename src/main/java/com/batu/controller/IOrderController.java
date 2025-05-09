package com.batu.controller;

import java.util.List;

import com.batu.dto.DtoOrder;

public interface IOrderController {

	public DtoOrder placeOrder(Long id);

	public DtoOrder getOrderByCode(String code);

	public List<DtoOrder> getOrdersByCustomer(Long id);
}
