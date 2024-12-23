package com.example.demo.repository;

import com.example.demo.model.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByStockSymbol(String stockSymbol); // 根據代碼查詢股票
    
    //用於推薦列表
    List<StockEntity> findTop5ByRiskLevel(String riskLevel);// 查詢符合條件的前五筆產品
}
