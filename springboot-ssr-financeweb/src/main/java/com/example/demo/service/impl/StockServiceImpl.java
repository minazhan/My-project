package com.example.demo.service.impl;

import com.example.demo.mapper.StockEntityMapper;
import com.example.demo.model.dto.StockDto;
import com.example.demo.model.entity.StockCodeEntity;
import com.example.demo.model.entity.StockEntity;
import com.example.demo.repository.StockCodeRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockCodeRepository stockCodeRepository; //從這裡獲取原始數據

    @Autowired
    private StockRepository stockRepository; //保存到這裡
    
    @Autowired
    private StockEntityMapper stockEntityMapper; //轉換

    public void updateStocks() {
    	
    	//清空表並重置自增列，id就不會一直增加
    	stockRepository.truncateTable();
    	
        //從資料庫獲取所有股票數據
        List<StockCodeEntity> stocks = stockCodeRepository.findAll();
        
        //跳過第一筆資料，因第一筆從股票網站抓下來無法過濾
        List<StockCodeEntity> stocksToProcess = stocks.subList(1, stocks.size());

        for (StockCodeEntity stock : stocksToProcess) {
            try {
                //根據成交量分類風險
                String riskLevel = classifyRiskByVolume(stock.getVolume());

                //保存到目標表 (StockEntity)
                saveToStockEntity(stock, riskLevel);
            } catch (Exception e) {
                System.err.println("分析股票數據失敗: " + stock.getStockSymbol() + " 錯誤: " + e.getMessage());
            }
        }

        System.out.println("股票數據分析完成！");
    }

    //根據成交量分類風險
    private String classifyRiskByVolume(Long volume) {
        if (volume == null || volume == 0L) return "未知風險";
        if (volume < 10000) return "低風險";
        if (volume < 50000) return "中風險";
        return "高風險";
    }

    //保存或更新數據到 StockEntity 表
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
    
    //查詢所有股票資料
    @Override
	public List<StockDto> getAllStocks() {
    	return stockRepository.findAll()
    		.stream()
		.map(stock -> stockEntityMapper.toDto(stock))
		.collect(Collectors.toList());
    }
    
}
