package com.example.demo.model.entity;

//存放市面上收集來的投資商品的相關資料，包括風險等級。
public class Product {

	private int productId;//投資商品的唯一編號（主鍵）
	private String productName;//投資商品的名稱
	//private String productType;投資商品的類型（如股票、基金、債券等）
	private String riskLevel;//投資商品的風險等級（例如通過標準差計算得出的風險評級）
	private float expectedReturn;//預期的收益率
	//private float standard;//標準差
	//private float sharRatio;//夏普比率
}
