package com.example.demo.model.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transaction")
public class Transaction {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;//每筆交易的唯一編號（主鍵）
	
	
	//referencedColumnName = "user_id"：User 表中對應的主鍵列名稱
	//nullable = false：不允許外鍵為空
    @ManyToOne //多個交易對應一個用戶
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false) 
    private User user; //外鍵關聯到 User 表
    
    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId; // 只讀屬性，用於直接訪問 user_id 值
	
	@Column(name = "transaction_date")
	private Date transactionDate;//收入支出日期
	
	@Column(name = "transaction_type")
	private String transactionType;//交易類型（如收入或支出）
	
	@Column(name = "expense")
	private Float expense;//交易金額
	
	@Column(name = "category")
	private String category;//交易分類（如食物、娛樂、薪資等）
}
