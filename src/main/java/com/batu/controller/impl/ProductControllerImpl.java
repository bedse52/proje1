package com.batu.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batu.controller.IProductController;
import com.batu.dto.DtoProduct;
import com.batu.entity.Product;
import com.batu.service.IProductService;

@RestController
@RequestMapping(path =  "rest/api/product")
public class ProductControllerImpl implements IProductController{

	@Autowired
	private IProductService productService;

	
	@Override
	@PostMapping(path = "/save")
	public DtoProduct createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
		
	}


	@Override
	@GetMapping("/list/{id}")
	public DtoProduct getProductbyId(@PathVariable(name = "id") Long id) {
		return productService.getProductbyId(id);
	}


	@Override
	@GetMapping("/list")
	public List<DtoProduct> getAllProducts() {
		return productService.getAllProducts();
	}


	@Override
	@PutMapping("/update/{id}")
	public DtoProduct updateProduct(@PathVariable(name = "id") Long id,@RequestBody DtoProduct dtoProduct) {
		return productService.updateProduct(id, dtoProduct);
	}


	@Override
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable(name ="id") Long id) {
		productService.deleteProduct(id);
	}
}
