package com.example.demo.controller.rest;

import com.example.demo.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockRestController {

    @Autowired
    private StockService stockService;

    @GetMapping("/update")
    public String updateStockData() {
        try {
            stockService.updateStocks();
            return "股票數據更新完成！";
        } catch (Exception e) {
            return "更新失敗：" + e.getMessage();
        }
    }
}
