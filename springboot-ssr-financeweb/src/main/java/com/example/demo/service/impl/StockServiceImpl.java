package com.example.demo.service.impl;

import com.example.demo.model.entity.StockCodeEntity;
import com.example.demo.model.entity.StockEntity;
import com.example.demo.repository.StockCodeRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockCodeRepository stockCodeRepository; // 從這裡獲取原始數據

    @Autowired
    private StockRepository stockRepository; // 保存到這裡

    public void updateStocks() {
        // 從資料庫獲取所有股票數據
        List<StockCodeEntity> stocks = stockCodeRepository.findAll();

        for (StockCodeEntity stock : stocks) {
            try {
                // 根據成交量分類風險
                String riskLevel = classifyRiskByVolume(stock.getVolume());

                // 保存到目標表 (StockEntity)
                saveToStockEntity(stock, riskLevel);
            } catch (Exception e) {
                System.err.println("分析股票數據失敗: " + stock.getStockSymbol() + " 錯誤: " + e.getMessage());
            }
        }

        System.out.println("股票數據分析完成！");
    }

    // 根據成交量分類風險
    private String classifyRiskByVolume(Long volume) {
        if (volume == null || volume == 0L) return "未知風險";
        if (volume < 100000) return "低風險";
        if (volume < 1000000) return "中風險";
        return "高風險";
    }

    // 保存或更新數據到 StockEntity 表
    private void saveToStockEntity(StockCodeEntity stock, String riskLevel) {
        StockEntity stockEntity = stockRepository.findByStockSymbol(stock.getStockSymbol())
                .orElse(new StockEntity());

        stockEntity.setStockSymbol(stock.getStockSymbol());
        stockEntity.setStockName(stock.getStockName());
        stockEntity.setVolume(stock.getVolume());
        stockEntity.setPrice(stock.getPrice());
        stockEntity.setRiskLevel(riskLevel);
        stockEntity.setLastUpdated(LocalDateTime.now());

        stockRepository.save(stockEntity);
    }
}
