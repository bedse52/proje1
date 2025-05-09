package com.batu.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batu.controller.ICustomerController;
import com.batu.dto.DtoCustomer;
import com.batu.entity.Customer;
import com.batu.service.ICustomerService;

@RestController
@RequestMapping(path =  "rest/api/customer")
public class CustomerControllerImpl implements ICustomerController{

	@Autowired
	private ICustomerService customerService;
	
	@PostMapping(path = "/save")
	@Override
	public DtoCustomer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
		 
	}

	@Override
	@GetMapping(path = "/list/{id}")
	public DtoCustomer getCustomerById(@PathVariable(name = "id") Long id) {
		return customerService.getCustomerById(id);
	}

	@Override
	@GetMapping(path = "/list")
	public List<DtoCustomer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

}
