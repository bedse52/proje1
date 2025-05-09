package com.batu.service;

import java.util.List;

import com.batu.dto.DtoCustomer;
import com.batu.entity.Customer;

public interface ICustomerService {

	public DtoCustomer addCustomer(Customer customer);
	
	public DtoCustomer getCustomerById(Long id);
	
	public List<DtoCustomer> getAllCustomers();
}
