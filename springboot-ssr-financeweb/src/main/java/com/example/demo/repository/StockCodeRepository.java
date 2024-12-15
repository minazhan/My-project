package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.StockCodeEntity;

@Repository
public interface StockCodeRepository extends JpaRepository<StockCodeEntity, Long> {
    
    // 查詢所有股票代碼
//    @Query("SELECT s.stockSymbol FROM StockCodeEntity s")
//    List<String> findAllSymbols();

    // 根據股票代碼查詢
    @Query("SELECT s FROM StockCodeEntity s WHERE s.stockSymbol = :stockSymbol")
    Optional<StockCodeEntity> findByStockSymbol(@Param("stockSymbol") String stockSymbol);
}
