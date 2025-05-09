package com.batu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batu.entity.Cart;
import com.batu.entity.InCart;
import com.batu.entity.Product;

@Repository
public interface InCartRepository extends JpaRepository<InCart, Long>{
	
	Optional<InCart> findByCartAndProduct(Cart cart, Product product);
}
