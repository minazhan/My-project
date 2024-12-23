//package com.example.demo.model.entity;
//
//import java.sql.Date;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
////存放推薦給使用者的投資商品
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "recommend")
//public class Recommend {
//
//	private int recommendationId;//推薦的唯一編號（主鍵）
//	private Integer userId;//對應 UserRiskResponse 的使用者 ID（外鍵）
//	private Long stockId;//對應 StockEntity 的股票 ID（外鍵）
//	private Date recommendationDate;//推薦日期
//	//private String recommendationReason;推薦原因（可選，說明為何推薦這個商品）
//}	