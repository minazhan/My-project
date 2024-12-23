package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.StockDto;

public interface StockService {

	void updateStocks();
	List<StockDto> getAllStocks();//查詢所有股票資料
}
