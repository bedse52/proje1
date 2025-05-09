package com.batu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batu.entity.PriceHistory;

@Repository
public interface PriceHistoryRepository  extends JpaRepository<PriceHistory, Long>{

}
