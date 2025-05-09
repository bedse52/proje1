package com.batu.controller;

import java.util.List;

import com.batu.dto.DtoCustomer;
import com.batu.entity.Customer;

public interface ICustomerController {

	public DtoCustomer addCustomer(Customer customer);

	public DtoCustomer getCustomerById(Long id);

	public List<DtoCustomer> getAllCustomers();
}
