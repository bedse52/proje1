package com.batu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batu.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
