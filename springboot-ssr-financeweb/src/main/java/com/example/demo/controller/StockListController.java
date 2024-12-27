package com.example.demo.controller;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.StockCodeDto;
import com.example.demo.model.dto.StockDto;
import com.example.demo.service.StockService;
import com.example.demo.service.YahooFinanceCrawlerService;

@Controller
@RequestMapping
public class StockListController {
	
	//用於抓取資料庫資料
	@Autowired
	private YahooFinanceCrawlerService yahooFinanceCrawlerService;
	
	//用於抓取資料庫資料
	@Autowired
	private StockService stockService;
	
	@GetMapping("/admin/stocks/user_stock_list")
	public String getStockEntity(Model model,@ModelAttribute StockDto stockDto) {
		List<StockDto> stockDtos = stockService.getAllStocks();
		
		//格式化成交量
	    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
	    List<Map<String, Object>> formattedStocks = stockDtos.stream().map(stock -> {
	    	Map<String, Object> stockMap = new HashMap<>();
	    	stockMap.put("stockId", stock.getStockId());
	        stockMap.put("stockSymbol", stock.getStockSymbol());
	        stockMap.put("stockName", stock.getStockName());
	        stockMap.put("volume", numberFormat.format(stock.getVolume())); // 格式化成交量
	        stockMap.put("riskLevel", stock.getRiskLevel());
	        stockMap.put("price", stock.getPrice());
	        stockMap.put("lastUpdated", stock.getLastUpdated());
	        return stockMap;
	    }).collect(Collectors.toList());
	    
	    // 獲取最新的更新時間
	    LocalDateTime lastUpdated = stockDtos.stream()
	        .map(StockDto::getLastUpdated)
	        .max(Comparator.naturalOrder())
	        .orElse(null); // 如果沒有數據，返回 null
	    
	    // 格式化為字符串（如果需要）
	    String formattedLastUpdated = lastUpdated != null 
	        ? lastUpdated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) 
	        : "無數據";
		
		model.addAttribute("stockDtos",formattedStocks);
		model.addAttribute("lastUpdated", formattedLastUpdated); // 添加最新更新時間到模型
		return "user/user_stock_list";
	}
	
	@GetMapping("/stocks/stock_list")
	public String  getStockCodeEntityList(Model model,@ModelAttribute StockCodeDto stockCodeDto) {
		List<StockCodeDto> stockCodeDtos = yahooFinanceCrawlerService.getAllStocks();
		// 分出前三名和剩下七個股票
	    //List<StockCodeDto> topStocks = stockCodeDtos.stream().limit(3).toList();
	    //List<StockCodeDto> remainingStocks = stockCodeDtos.stream().skip(3).limit(7).toList();
		
	    //格式化成交量
	    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
	    List<Map<String, Object>> formattedtopStocks = stockCodeDtos.stream().skip(1).limit(3).map(topStock -> {
	    	Map<String, Object> stockMap = new HashMap<>();
	    	stockMap.put("stockId", topStock.getId());
	        stockMap.put("stockSymbol", topStock.getStockSymbol());
	        stockMap.put("stockName", topStock.getStockName());
	        stockMap.put("volume", numberFormat.format(topStock.getVolume())); // 格式化成交量
	        stockMap.put("price", topStock.getPrice());
	        stockMap.put("lastUpdated", topStock.getLastUpdated());
	        return stockMap;
	    }).collect(Collectors.toList());
	    
	    List<Map<String, Object>> formattedremainingStocks = stockCodeDtos.stream().skip(4).limit(7).map(remainingStock -> {
	    	Map<String, Object> stockMap = new HashMap<>();
	    	stockMap.put("stockId", remainingStock.getId());
	        stockMap.put("stockSymbol", remainingStock.getStockSymbol());
	        stockMap.put("stockName", remainingStock.getStockName());
	        stockMap.put("volume", numberFormat.format(remainingStock.getVolume())); // 格式化成交量
	        stockMap.put("price", remainingStock.getPrice());
	        stockMap.put("lastUpdated", remainingStock.getLastUpdated());
	        return stockMap;
	    }).collect(Collectors.toList());
	    		
	    
	    
		model.addAttribute("topStocks",formattedtopStocks);
		model.addAttribute("remainingStocks",formattedremainingStocks);
		return "stock_list";
	}
}
