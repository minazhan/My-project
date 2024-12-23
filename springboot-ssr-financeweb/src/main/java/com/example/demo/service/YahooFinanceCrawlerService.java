package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.StockCodeDto;

public interface YahooFinanceCrawlerService {

	void fetchTopVolumeStocks();
	List<StockCodeDto> getAllStocks();//查詢所有股票資料
}
