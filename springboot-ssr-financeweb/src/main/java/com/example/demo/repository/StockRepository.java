package com.example.demo.repository;

import com.example.demo.model.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByStockSymbol(String stockSymbol); // 根據代碼查詢股票
}
