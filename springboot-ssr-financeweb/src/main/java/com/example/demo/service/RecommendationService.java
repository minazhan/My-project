package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.entity.StockEntity;

public interface RecommendationService {

	
	public String getUserRiskTypeFromDatabase(Integer userId); //根據使用者 ID 查詢風險類型

	public List<StockEntity> recommendTop5Products(Integer userId); //根據使用者的風險類型推薦前五筆產品
	
}
