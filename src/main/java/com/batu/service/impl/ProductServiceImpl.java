package com.batu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batu.dto.DtoProduct;
import com.batu.entity.PriceHistory;
import com.batu.entity.Product;
import com.batu.repository.PriceHistoryRepository;
import com.batu.repository.ProductRepository;
import com.batu.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PriceHistoryRepository priceHistoryRepository;

	@Override
	public DtoProduct createProduct(Product product) {
		DtoProduct postman = new DtoProduct();
		Product savedProduct = productRepository.save(product);
		BeanUtils.copyProperties(savedProduct, postman);
		
		PriceHistory history = new PriceHistory();
		history.setProduct(savedProduct);
		history.setPrice(savedProduct.getPrice());
		history.setValidFrom(savedProduct.getUpdatedAt());
		priceHistoryRepository.save(history);
		
		return postman;
	}

	@Override
	public DtoProduct getProductbyId(Long id) {
		DtoProduct dtoProduct = new DtoProduct();
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		Product dbProduct =optional.get();
		BeanUtils.copyProperties(dbProduct, dtoProduct);
		return dtoProduct;
	}

	@Override
	public List<DtoProduct> getAllProducts() {
		List<DtoProduct>  dtolist = new ArrayList<>();
		List<Product> productlist = productRepository.findAll();
		for(Product product : productlist) {
			DtoProduct dtoProduct = new DtoProduct();
			BeanUtils.copyProperties(product, dtoProduct);
			dtolist.add(dtoProduct);
		}
		
		return  dtolist;
	}

	@Override
	public DtoProduct updateProduct(Long id, DtoProduct dtoProduct) {
		Optional<Product> optional = productRepository.findById(id);
		DtoProduct dto = new DtoProduct();
		if (optional.isPresent()) {
			Product product =optional.get();
			Boolean priceChanged =!product.getPrice().equals(dtoProduct.getPrice());
			product.setName(dtoProduct.getName());
			product.setPrice(dtoProduct.getPrice());
			product.setStock(dtoProduct.getStock());
			Product updatedProduct = productRepository.save(product);
			BeanUtils.copyProperties(updatedProduct, dto);
			
			if(priceChanged) {
				PriceHistory history = new PriceHistory();
				history.setProduct(product);
				history.setPrice(dtoProduct.getPrice());
				history.setValidFrom(product.getUpdatedAt());
				priceHistoryRepository.save(history);
			}
			return dto;
		}
		return null;
		
	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.delete(optional.get());
		}
		
	}
	
}
