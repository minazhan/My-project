package com.example.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.impl.YahooFinanceCrawlerService;

@RestController
@RequestMapping("/crawler")
public class YahooCrawlerController {

    @Autowired
    private YahooFinanceCrawlerService crawlerService;

    @GetMapping("/fetch-volume")
    public String fetchVolumeRankStocks() {
        try {
            crawlerService.fetchTopVolumeStocks();
            return "成交量數據抓取完成！";
        } catch (Exception e) {
            return "抓取失敗：" + e.getMessage();
        }
    }
}

