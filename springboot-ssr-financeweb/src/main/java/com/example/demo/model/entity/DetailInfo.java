package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="detail_info")
public class DetailInfo {
	
	@Id
	@Column(name = "user_id")
	private Integer userId;//對應 User 資料表中的使用者 ID（主鍵/外鍵）
	
	//DetailInfo 和 User 之間是一對一的關係
	
	@Column(name = "month_revenue")
	private Float monthRevenue;//月收入
	
	@Column(name = "month_expense")
	private Float monthExpense;//月支出
	
	@Column(name = "month_balance")
	private Float monthBalance;//月餘額（可通過收入與支出的差額計算）
	
	@Column(name = "total_revenue")
	private Float totalRevenue;//總收入
	
	@Column(name = "total_expense")
	private Float totalExpense;//總支出
	
	@Column(name = "total_balance")
	private Float totalBalance;//總餘額（可通過總收入與總支出的差額計算）

}
