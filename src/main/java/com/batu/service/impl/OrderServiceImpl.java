package com.batu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batu.dto.DtoOrder;
import com.batu.dto.DtoOrderItems;
import com.batu.entity.Cart;
import com.batu.entity.InCart;
import com.batu.entity.Order;
import com.batu.entity.OrderItems;
import com.batu.entity.Product;
import com.batu.repository.CartRepository;
import com.batu.repository.OrderItemsRepository;
import com.batu.repository.OrderRepository;
import com.batu.repository.ProductRepository;
import com.batu.service.IOrderService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemsRepository orderItemsRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartServiveImpl cartService;

	private String generateNextOrderCode(String lastCode) {
		if (lastCode == null || lastCode.length() != 4) {
			return "AAAA";
		}

		char[] chars = lastCode.toCharArray();

		for (int i = 3; i >= 0; i--) {
			if (chars[i] != 'Z') {
				chars[i]++;
				break;
			} else {
				chars[i] = 'A';
			}
		}

		return new String(chars);
	}

	private DtoOrder dtoOrderCreater(Order order) {
		DtoOrder dtoOrder = new DtoOrder();
		BeanUtils.copyProperties(order, dtoOrder);
		dtoOrder.setCustomer(order.getCustomer().getId());

		for (OrderItems orderItems : order.getOrderItems()) {
			DtoOrderItems dto = new DtoOrderItems();
			BeanUtils.copyProperties(orderItems, dto);
			dto.setProductId(orderItems.getProduct().getId());
			dtoOrder.getOrderItems().add(dto);
		}
		return dtoOrder;
	}

	@Transactional
	@Override
	public DtoOrder placeOrder(Long id) {
		cartService.updateCart(id);
		Cart cart = cartService.cartEntity(id);
		String lastCode = orderRepository.findTopByOrderByCreatedAtDesc().map(Order::getOrderCode).orElse(null);
		if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
			throw new RuntimeException("Cart is empty cant place order");
		}
		for (InCart inCart : cart.getCartItems()) {
			Product product = inCart.getProduct();
			if (product.getStock() < inCart.getQuantity()) {
				throw new RuntimeException("Insufficient stock for product: " + product.getName());
			}
		}
		Order order = new Order();
		order.setCustomer(cart.getCustomer());
		String newCode = generateNextOrderCode(lastCode);
		order.setOrderCode(newCode);
		order.setOrderItems(new ArrayList<>());
		BigDecimal totalPrice = BigDecimal.ZERO;

		for (InCart inCart : cart.getCartItems()) {
			Product product = inCart.getProduct();

			product.setStock(product.getStock() - inCart.getQuantity());
			productRepository.save(product);

			OrderItems orderItems = new OrderItems();
			orderItems.setOrder(order);
			orderItems.setProduct(product);
			orderItems.setPrice(inCart.getPrice());
			orderItems.setSubtotal(inCart.getSubtotal());
			orderItems.setQuantity(inCart.getQuantity());
			orderItemsRepository.save(orderItems);

			order.getOrderItems().add(orderItems);
			totalPrice = totalPrice.add(orderItems.getSubtotal());
		}
		order.setTotalPrice(totalPrice);
		orderRepository.save(order);

		cart.getCartItems().clear();
		cart.setTotalPrice(BigDecimal.ZERO);
		cartRepository.save(cart);

		DtoOrder dtoOrder = dtoOrderCreater(order);
		return dtoOrder;
	}

	@Override
	public DtoOrder getOrderByCode(String code) {
		Optional<Order> optional = orderRepository.findByOrderCode(code);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Order with code " + code + " not found.");
		}
		Order order = optional.get();
		DtoOrder dtoOrder = dtoOrderCreater(order);
		return dtoOrder;
	}

	@Override
	public List<DtoOrder> getOrdersByCustomer(Long id) {
		 List<Order> orders = orderRepository.findByCustomerId(id);
		    List<DtoOrder> dtoOrders = new ArrayList<>();

		    for (Order order : orders) {
		        dtoOrders.add(dtoOrderCreater(order));
		    }

		    return dtoOrders;
	}

}
