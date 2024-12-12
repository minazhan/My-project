package com.example.demo.model.entity;

import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_risk_response")
public class UserRiskResponse {
	
	@Id
	@Column(name = "user_id")
	private Integer userId; //使用者ID（主鍵/外鍵），刪除就一併刪除嗎???
	
	@OneToOne //UserRiskResponse 和 User 之間是一對一的關係
    @MapsId  // 使用 MapsId 來表示 userId 既是主鍵也是外鍵
    @JoinColumn(name = "user_id")  // 指定這個欄位是外鍵對應到 User 表
    private User user;
	
	@Column(name = "total_score")
	private Integer totalScore; //使用者問卷總分
	
	@Column(name = "risk_type")
	private String riskType; //風險類型
	
	@Column(name = "user_answers_json", columnDefinition = "TEXT")
	private String userAnswersJson; //追蹤用戶回答 //存儲 JSON 格式的用戶回答

}
