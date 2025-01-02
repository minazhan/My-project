package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.StockEntity;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.UserRiskResponseRepository;
import com.example.demo.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService{

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private UserRiskResponseRepository userRiskResponseRepository;
	
	
    //風險類型與等級的對應邏輯
    private String mapRiskTypeToRiskLevel(String riskType) {
        switch (riskType) {
            case "保守型":
                return "低";
            case "穩健型":
                return "中";
            case "積極型":
                return "高";
            default:
                throw new IllegalArgumentException("未知的風險類型: " + riskType);
        }
    }
	
	//根據使用者 ID 查詢風險類型
	@Override
	public String getUserRiskTypeFromDatabase(Integer userId) {
		return userRiskResponseRepository.findRiskTypeByUserId(userId);
	}

	//根據使用者的風險類型推薦前五筆產品
	@Override
	public List<StockEntity> recommendTop5Products(Integer userId) {
	//從資料庫查詢風險類型
        String userRiskType = getUserRiskTypeFromDatabase(userId);

        //取得對應的風險等級
        String riskLevel = mapRiskTypeToRiskLevel(userRiskType);

        //查詢前五筆產品
        return stockRepository.findTop5ByRiskLevel(riskLevel);
	}

}
