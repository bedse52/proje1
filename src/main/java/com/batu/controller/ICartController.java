package com.batu.controller;

import com.batu.dto.DtoCart;
import com.batu.dto.DtoInCart;
import com.batu.dto.DtoInCartIU;

public interface ICartController {

	public DtoCart getCartByCustomerId(Long customerId);

	public DtoCart updateCart(Long customerId);

	public DtoCart emptyCart(Long customerId);

	public DtoInCart addItem(Long customerId, DtoInCartIU request);

	public DtoCart removeItem(Long customerId, DtoInCartIU request);
}
