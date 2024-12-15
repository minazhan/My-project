package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//儲存股票代碼清單
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_codes")
public class StockCodeEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主鍵

    @Column(name = "stock_symbol", unique = true, nullable = false)
    private String stockSymbol; // 股票代碼

    @Column(name = "stock_name")
    private String stockName; // 股票名稱

    //@Column(name = "market_type")
    //private String marketType;  上市類型（如上市、上櫃）

    //DEFAULT CURRENT_TIMESTAMP：插入時自動設置當前時間
    //ON UPDATE CURRENT_TIMESTAMP：更新記錄時自動更新時間
    
    @Column(name = "volumn")
    private Long volume;//成交量
    
    @Column(name = "price")
    private Double price;//成交量
    
    @Column(name = "last_updated", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated; // 最近更新時間
}
