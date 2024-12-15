package com.example.demo.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

	private Long stockId;//投資商品的唯一編號（主鍵）
	private String stockSymbol;//投資商品的代碼
	private String stockName;//投資商品的名稱
	private Double price; //當前價格
	private Long volume;//最近更新時間
	private String riskLevel;//投資商品的風險等級（例如通過標準差計算得出的風險評級）
	private LocalDateTime lastUpdated;
}
