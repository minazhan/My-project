package com.example.demo.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 存放市面上收集來的投資商品的相關資料，包括風險等級
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId; // 投資商品的唯一編號（主鍵）

    @Column(name = "stock_symbol", unique = true, nullable = false)
    private String stockSymbol; // 投資商品的代碼

    @Column(name = "stock_name")
    private String stockName; // 投資商品的名稱

    @Column(name = "price")
    private Double price;  //當前價格

    @Column(name = "volume")
    private Long volume; // 股票的成交量

    @Column(name = "risk_level")
    private String riskLevel; // 投資商品的風險等級

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated; // 最近更新時間
}
