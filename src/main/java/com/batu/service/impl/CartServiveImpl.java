package com.batu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batu.dto.DtoCart;
import com.batu.dto.DtoInCart;
import com.batu.dto.DtoInCartIU;
import com.batu.entity.Cart;
import com.batu.entity.Customer;
import com.batu.entity.InCart;
import com.batu.entity.Product;
import com.batu.repository.CartRepository;
import com.batu.repository.CustomerRepository;
import com.batu.repository.InCartRepository;
import com.batu.repository.ProductRepository;
import com.batu.service.ICartService;

@Service
public class CartServiveImpl implements ICartService {


	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private InCartRepository inCartRepository;

	@Autowired
	private ProductRepository productRepository;

	public Cart cartEntity(Long customerId) {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isEmpty()) {
			return null;
		}
		Customer customer = optional.get();
		Cart cart = customer.getCart();
		return cart;
	}

	public Product productEntity(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		Product product = optional.get();
		return product;
	}

	@Override
	public DtoCart getCartByCustomerId(Long customerId) {
		DtoCart dtoCart = new DtoCart();
		Cart cart = cartEntity(customerId);
		BeanUtils.copyProperties(cart, dtoCart);
		if (cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
			for (InCart inCart : cart.getCartItems()) {
				DtoInCart dtoInCart = new DtoInCart();
				BeanUtils.copyProperties(inCart, dtoInCart);
				dtoInCart.setProductId(inCart.getProduct().getId());
				dtoCart.getInCart().add(dtoInCart);
			}
		}
		return dtoCart;
	}

	@Override
	public DtoCart updateCart(Long customerId) {
		Cart cart = cartEntity(customerId);
		BigDecimal total = BigDecimal.ZERO;
		if (cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
			for (InCart inCart : cart.getCartItems()) {
				Product product =productEntity(inCart.getProduct().getId());
				BigDecimal price = product.getPrice();
				
				if (inCart.getPrice().compareTo(price) !=0) {
					inCart.setPrice(price);
					inCart.setSubtotal(price.multiply(BigDecimal.valueOf(inCart.getQuantity())));
					inCartRepository.save(inCart);
				}
				total = total.add(inCart.getSubtotal());
			}
		}
		cart.setTotalPrice(total);
		Cart updatedCart = cartRepository.save(cart);
		
		DtoCart dtoCart = getCartByCustomerId(customerId);
		BeanUtils.copyProperties(updatedCart, dtoCart);
		return dtoCart;
	}

	@Override
	public DtoCart emptyCart(Long customerId) {
		Cart cart = cartEntity(customerId);
		if (cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
			cart.getCartItems().clear();
		}
		cart.setTotalPrice(BigDecimal.ZERO);
		Cart updatedCart = cartRepository.save(cart);
		DtoCart dtoCart = new DtoCart();
		BeanUtils.copyProperties(updatedCart, dtoCart);
		dtoCart.setInCart(new ArrayList<>());
		return dtoCart;
	}

	@Override
	public DtoInCart addItem(Long customerId, DtoInCartIU request) {
		
		Product product = productEntity(request.getProductId());
		BigDecimal price = product.getPrice();
		Integer quantity = request.getQuantity();
		Cart cart = cartEntity(customerId);
		
		Optional<InCart> optinal = inCartRepository.findByCartAndProduct(cart, product);
		
		InCart inCart;
		if (optinal.isPresent()) {
			inCart = optinal.get();
			Integer newQuantity = inCart.getQuantity()+quantity;
			inCart.setQuantity(newQuantity);
			inCart.setSubtotal(price.multiply(BigDecimal.valueOf(newQuantity)));
		} else {
			inCart = new InCart();
			inCart.setCart(cart);
			inCart.setProduct(product);
			inCart.setPrice(price);
			inCart.setQuantity(quantity);
			inCart.setSubtotal(price.multiply(BigDecimal.valueOf(quantity)));
			
		}
		
		inCartRepository.save(inCart);
		
		DtoInCart dtoInCart = new DtoInCart();
		BeanUtils.copyProperties(inCart, dtoInCart);
		dtoInCart.setProductId(inCart.getProduct().getId());
		return dtoInCart;
	}

	@Override
	public DtoCart removeItem(Long customerId, DtoInCartIU request){
		Product product = productEntity(request.getProductId());
		Integer removeQuantity = request.getQuantity();
		Cart cart = cartEntity(customerId);
		BigDecimal price = product.getPrice();
		Optional<InCart> optional = inCartRepository.findByCartAndProduct(cart, product);
		if (optional.isPresent()) {
			InCart inCart = optional.get();
			Integer currentQuantity= inCart.getQuantity();
			if (removeQuantity > currentQuantity) {
				throw new IllegalArgumentException("Cannot remove more than current quantity.");
			}
			if (removeQuantity == currentQuantity) {
				inCartRepository.delete(inCart);
				
			}else {
				inCart.setQuantity(currentQuantity - removeQuantity); 
				inCart.setSubtotal(price.multiply(BigDecimal.valueOf(inCart.getQuantity())));
				inCartRepository.save(inCart);
			}
		}
		return updateCart(customerId);
	}

}
