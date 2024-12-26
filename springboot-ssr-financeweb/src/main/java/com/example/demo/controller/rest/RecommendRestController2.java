package com.example.demo.controller.rest;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.StockEntity;
import com.example.demo.service.CalculateBalance;
import com.example.demo.service.RecommendationService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/recommend")
public class RecommendRestController2 {
	
	@Autowired
	private RecommendationService recommendationService;
	
	@Autowired
	private CalculateBalance calculateBalance;

	//根據使用者 ID 推薦前五筆產品("/{userId}")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getTop5Recommendations(HttpSession session) {
        
    	//從 Session 獲取 UserCert
        UserCert userCert = (UserCert) session.getAttribute("userCert");
        if (userCert == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        
        //如果已登入，獲取 userId
        Integer userId = userCert.getUserId();
    	
    	//調用 Service 層獲取推薦產品
        List<StockEntity> recommendedStocks = recommendationService.recommendTop5Products(userId);

        //計算餘額
        BigDecimal balance = calculateBalance.calculateBalance(userId);
        
        //格式化
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        
        //格式化股票價格
        List<Map<String, Object>> formattedStocks = recommendedStocks.stream().map(stock -> {
            Map<String, Object> stockMap = new HashMap<>();
            stockMap.put("stockId", stock.getStockId());
            stockMap.put("stockSymbol", stock.getStockSymbol());
            stockMap.put("stockName", stock.getStockName());
            stockMap.put("price", stock.getPrice());
            stockMap.put("volume", numberFormat.format(stock.getVolume())); // 格式化成交量
            stockMap.put("riskLevel", stock.getRiskLevel());
            stockMap.put("lastUpdated", stock.getLastUpdated());
            return stockMap;
        }).collect(Collectors.toList());
        
        //格式化餘額
        String formattedBalance = numberFormat.format(balance);
        
        //將推薦產品與餘額組合
        Map<String, Object> response = new HashMap<>();
        response.put("recommendations", formattedStocks); // 推薦產品列表
        response.put("balance", formattedBalance); // 餘額

        
        //返回前五筆產品清單
        return ResponseEntity.ok(response);
    }
}
