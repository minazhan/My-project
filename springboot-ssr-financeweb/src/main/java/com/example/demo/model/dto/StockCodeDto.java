package com.example.demo.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCodeDto {

	private Long id; // 主鍵
	private String stockSymbol; // 股票代碼
	private String stockName; // 股票名稱
	private Long volume;
	private Double price;//成交量
	private LocalDateTime lastUpdated; // 最近更新時間
}
