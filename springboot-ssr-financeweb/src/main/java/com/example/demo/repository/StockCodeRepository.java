package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.StockCodeEntity;

import jakarta.transaction.Transactional;

@Repository
public interface StockCodeRepository extends JpaRepository<StockCodeEntity, Long> {

    // 根據股票代碼查詢
    @Query("SELECT s FROM StockCodeEntity s WHERE s.stockSymbol = :stockSymbol")
    Optional<StockCodeEntity> findByStockSymbol(@Param("stockSymbol") String stockSymbol);
    
    //TRUNCATE完成重置自增列的工作
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE stock_codes", nativeQuery = true)
    void truncateTable();
}
