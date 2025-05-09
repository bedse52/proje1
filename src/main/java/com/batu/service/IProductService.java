package com.batu.service;

import java.util.List;

import com.batu.dto.DtoProduct;
import com.batu.entity.Product;

public interface IProductService {

	 public DtoProduct createProduct(Product product);
	 
	 public DtoProduct getProductbyId(Long id);
	 
	 public List<DtoProduct> getAllProducts();
	 
	 public DtoProduct updateProduct(Long id, DtoProduct dtoProduct);
	 
	 public void deleteProduct(Long id);
}
