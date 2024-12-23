package com.example.demo.controller;

import java.util.List;

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
@RequestMapping("/stocks")
public class StockListController {
	
	//用於抓取資料庫資料
	@Autowired
	private YahooFinanceCrawlerService yahooFinanceCrawlerService;
	
	//用於抓取資料庫資料
	@Autowired
	private StockService stockService;
	
	@GetMapping("/user_stock_list")
	public String getStockEntity(Model model,@ModelAttribute StockDto stockDto) {
		List<StockDto> stockDtos = stockService.getAllStocks();
		model.addAttribute("stockDtos",stockDtos);
		return "user/user_stock_list";
	}
	
	@GetMapping("/stock_list")
	public String  getStockCodeEntityList(Model model,@ModelAttribute StockCodeDto stockCodeDto) {
		List<StockCodeDto> stockCodeDtos = yahooFinanceCrawlerService.getAllStocks();
		// 分出前三名和剩下七個股票
	    List<StockCodeDto> topStocks = stockCodeDtos.stream().limit(3).toList();
	    List<StockCodeDto> remainingStocks = stockCodeDtos.stream().skip(3).limit(7).toList();
		
		model.addAttribute("topStocks",topStocks);
		model.addAttribute("remainingStocks",remainingStocks);
		return "stock_list";
	}
}
