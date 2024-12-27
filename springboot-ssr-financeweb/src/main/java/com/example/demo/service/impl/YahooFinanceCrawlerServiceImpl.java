package com.example.demo.service.impl;

import com.example.demo.mapper.StockCodeEntityMapper;
import com.example.demo.model.dto.StockCodeDto;
import com.example.demo.model.entity.StockCodeEntity;
import com.example.demo.repository.StockCodeRepository;
import com.example.demo.service.YahooFinanceCrawlerService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YahooFinanceCrawlerServiceImpl implements YahooFinanceCrawlerService{

    @Autowired
    private StockCodeRepository stockCodeRepository;
    
    @Autowired
    private StockCodeEntityMapper stockCodeEntityMapper;

    private static final String VOLUME_RANK_URL = "https://tw.stock.yahoo.com/tw-etf/volume";

    public void fetchTopVolumeStocks() {
    	
    	// 清空表並重置自增列
    	stockCodeRepository.truncateTable();
    	
        try {
            // 連接到 Yahoo 股市的成交量排行榜頁面
            Document document = Jsoup.connect(VOLUME_RANK_URL).get();

            // 找到包含股票資料的所有 <li> 標籤
            Elements stockRows = document.select("ul > li");
            
            // 移除第一筆資料
//            if (!stockRows.isEmpty()) {
//                stockRows.remove(0);
//            }

            boolean isFirstRow = true; // 用於標記第一筆資料
            for (Element row : stockRows) {
                try {
                	
                    if (isFirstRow) {
                        isFirstRow = false; // 標記第一筆資料已處理
                        continue; // 跳過第一筆資料
                    }
                	
                    // 獲取股票代碼，並去掉後綴
                    String stockSymbol = row.select("span").stream()
                            .filter(span -> span.text().endsWith(".TW") || span.text().endsWith(".TWO"))
                            .map(span -> span.text().replaceAll("\\.(TW|TWO)$", "")) // 使用正則表達式同時匹配 .TW 和 .TWO
                            .findFirst()
                            .orElse("未知代碼");
 
                    // 獲取股票詳細頁的連結
                    String stockLink = row.select("a[href]").attr("href");
                    if (!stockLink.startsWith("https://")) {
                        stockLink = "https://tw.stock.yahoo.com" + stockLink; // 添加主域名
                    }

                    // 進入股票詳細頁抓取名稱
                    String stockName = fetchStockNameFromDetailPage(stockLink);

                    // 獲取成交量，確保只抓取 class="Jc(fe)" 的特定 <span>
                    String volumeText = row.select("span.Jc\\(fe\\)").stream()
                            .reduce((first, last) -> last) // 獲取最後一個符合條件的<span>
                            .map(Element::text)
                            .orElse("0"); // 如果抓不到，返回 "0"

                    Long volume = parseVolume(volumeText);

                    
                 // 獲取價格，確保只抓取 class="Jc(fe) Fw(600) D(f) Ai(c) C($c-trend-down)" 的特定 <span>
                    String priceText = row.select("span.Jc\\(fe\\).Fw\\(600\\)").stream()
                            .filter(span -> span.parent().className().contains("Miw(80px)")) // 限定父容器
                            .map(Element::text)
                            .findFirst()
                            .orElse("0"); // 如果抓不到，預設為 "0"

                    // 將價格轉換為 Double
                    Double price = parsePrice(priceText);

                    

                    // 保存到資料庫
                    saveStockData(stockSymbol, stockName, volume,price);
                } catch (Exception e) {
                    System.err.println("解析股票數據失敗: " + e.getMessage());
                }
            }

            System.out.println("Yahoo 股市數據抓取完成！");
        } catch (Exception e) {
            System.err.println("抓取 Yahoo 股市數據失敗: " + e.getMessage());
        }
    }

    // 進入股票詳細頁面抓取 <h1> 中的名稱
    private String fetchStockNameFromDetailPage(String stockLink) {
        try {
            Document detailPage = Jsoup.connect(stockLink).get();
            Element h1Element = detailPage.selectFirst("h1.C\\(\\$c-link-text\\)");
            return h1Element != null ? h1Element.text().trim() : "未知名稱";
        } catch (Exception e) {
            System.err.println("抓取詳細頁股票名稱失敗: " + e.getMessage());
            return "未知名稱";
        }
    }

    // 將成交量字串轉換為 Long
    private Long parseVolume(String volumeText) {
        try {
            // 去掉可能的逗號，例如 "124,240" -> "124240"
            return Long.parseLong(volumeText.replace(",", ""));           
        } catch (NumberFormatException e) {
            return 0L; // 如果解析失敗，返回 0
        }
    }
    
    
    // 將價格字串轉換為 Double
    private Double parsePrice(String priceText) {
        try {
            // 去掉逗號和空白，確保格式正確
            return Double.parseDouble(priceText.trim().replace(",", ""));
        } catch (NumberFormatException e) {
            System.err.println("價格轉換失敗：" + priceText);
            return 0.0;
        }
    }

    // 保存股票數據到資料庫
    private void saveStockData(String stockSymbol, String stockName, Long volume,Double price) {
        // 檢查是否已存在該股票代碼
        if (stockCodeRepository.findByStockSymbol(stockSymbol).isEmpty()) {
            StockCodeEntity stockCodeEntity = new StockCodeEntity();
            stockCodeEntity.setStockSymbol(stockSymbol);
            stockCodeEntity.setStockName(stockName);
            stockCodeEntity.setVolume(volume);
            stockCodeEntity.setPrice(price);
            stockCodeEntity.setLastUpdated(LocalDateTime.now());
            stockCodeRepository.save(stockCodeEntity);
        } else {
            System.out.println("股票代碼已存在，跳過保存：" + stockSymbol);
        }
    }

    //查詢所有股票資料
	@Override
	public List<StockCodeDto> getAllStocks() {
		return stockCodeRepository.findAll()
				.stream()
				.map(stockCode -> stockCodeEntityMapper.toDto(stockCode))
				.collect(Collectors.toList());
	}
}
