package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.StockEntity;
import com.example.demo.service.RecommendationService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/recommend")
public class RecommendRestController {
	
	@Autowired
	private RecommendationService recommendationService;

	// 根據使用者 ID 推薦前五筆產品("/{userId}")
    @GetMapping
    public ResponseEntity<List<StockEntity>> getTop5Recommendations(HttpSession session) {
        
    	// 從 Session 獲取 UserCert
        UserCert userCert = (UserCert) session.getAttribute("userCert");
        if (userCert == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        
        // 如果已登入，獲取 userId
        Integer userId = userCert.getUserId();
    	
    	// 調用 Service 層獲取推薦產品
        List<StockEntity> recommendedStocks = recommendationService.recommendTop5Products(userId);

        // 返回前五筆產品清單
        return ResponseEntity.ok(recommendedStocks);
    }
}
