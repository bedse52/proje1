package com.batu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batu.dto.DtoCustomer;
import com.batu.entity.Cart;
import com.batu.entity.Customer;
import com.batu.repository.CartRepository;
import com.batu.repository.CustomerRepository;
import com.batu.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public DtoCustomer addCustomer(Customer customer) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		Customer savedCustomer = customerRepository.save(customer);
		
		Cart cart = new Cart();
		cart.setCustomer(savedCustomer);
		cart.setTotalPrice(BigDecimal.ZERO);
		cartRepository.save(cart);
		customerRepository.save(savedCustomer);
		savedCustomer.setCart(cart);
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		return dtoCustomer;
	}

	@Override
	public DtoCustomer getCustomerById(Long id) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		Customer customer =optional.get();
		BeanUtils.copyProperties(customer, dtoCustomer);
		return dtoCustomer;
	}

	@Override
	public List<DtoCustomer> getAllCustomers() {
		List<DtoCustomer>  dtolist = new ArrayList<>();
		List<Customer> customerList = customerRepository.findAll();
		for(Customer customer : customerList) {
			DtoCustomer dtoCustomer = new DtoCustomer();
			BeanUtils.copyProperties(customer, dtoCustomer);
			dtolist.add(dtoCustomer);
		}
		
		return  dtolist;
	}

}