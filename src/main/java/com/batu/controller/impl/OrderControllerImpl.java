package com.batu.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batu.controller.IOrderController;
import com.batu.dto.DtoOrder;
import com.batu.service.IOrderService;

@RestController
@RequestMapping("/rest/api/order")
public class OrderControllerImpl implements IOrderController{

	@Autowired
	private IOrderService orderService;

	@Override
	@PostMapping("/place/{id}")
	public DtoOrder placeOrder(@PathVariable(name = "id") Long id) {
		return orderService.placeOrder(id);
	}

	@Override
	@GetMapping("/{orderCode}")
	public DtoOrder getOrderByCode(@PathVariable(name = "orderCode") String code) {
		return orderService.getOrderByCode(code);
	}

	@Override
	@GetMapping("/list/{id}")
	public List<DtoOrder> getOrdersByCustomer(@PathVariable(name = "id") Long id) {
		return orderService.getOrdersByCustomer(id);
	}
}
