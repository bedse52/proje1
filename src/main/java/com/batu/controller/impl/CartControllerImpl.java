package com.batu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batu.controller.ICartController;
import com.batu.dto.DtoCart;
import com.batu.dto.DtoInCart;
import com.batu.dto.DtoInCartIU;
import com.batu.service.ICartService;

@RestController
@RequestMapping("/rest/api/cart")
public class CartControllerImpl implements ICartController{

	@Autowired
	private ICartService cartService;
	
	@Override
	@GetMapping("/{id}")
	public DtoCart getCartByCustomerId(@PathVariable(name = "id") Long customerId) {
		return cartService.getCartByCustomerId(customerId);
	}

	@Override
	@PutMapping("/update/{id}")
	public DtoCart updateCart(@PathVariable(name = "id") Long customerId) {
		return cartService.updateCart(customerId);
	}

	@Override
	@DeleteMapping("/empty/{id}")
	public DtoCart emptyCart(@PathVariable(name = "id") Long customerId) {
		return cartService.emptyCart(customerId);
	}

	@Override
	@PostMapping("/add/{id}")
	public DtoInCart addItem(@PathVariable(name = "id") Long customerId,@RequestBody DtoInCartIU request) {
		return cartService.addItem(customerId, request);
	}

	@Override
	@PatchMapping("/remove/{id}")
	public DtoCart removeItem(@PathVariable(name = "id") Long customerId,@RequestBody DtoInCartIU request) {
		return cartService.removeItem(customerId, request);
		
	}

}
