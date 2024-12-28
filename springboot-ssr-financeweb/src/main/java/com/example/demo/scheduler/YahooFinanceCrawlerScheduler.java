package com.example.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.YahooFinanceCrawlerService;

@Component
public class YahooFinanceCrawlerScheduler {

    @Autowired
    private YahooFinanceCrawlerService crawlerService;

    // 每天 13:30 自動執行
    @Scheduled(cron = "0 30 13 * * ?") 
    public void fetchVolumeRankStocks() {
        try {
            crawlerService.fetchTopVolumeStocks();
            System.out.println("下午 1:30 成交量數據抓取完成！");
        } catch (Exception e) {
            System.err.println("抓取失敗：" + e.getMessage());
        }
    }
}