package com.example.demo.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) //主鍵是由資料庫自動生成，user_id自動生成,從1開始每次自動+1過號不補
		@Column(name = "user_id")
		private Integer userId;//使用者ID
		
		//mappedBy 表示由 UserRiskResponse 中的 user 屬性來維護這個關聯
		//orphanRemoval = true：這個屬性確保當一個 User 不再引用某個 UserRiskResponse 時，這個 UserRiskResponse 會被自動移除
		@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	    private UserRiskResponse userRiskResponse;  // 風險評估資料
		
		//需要雙向關聯（從 User 實體中也能查詢對應的 Transaction）       , orphanRemoval = true
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
	    private List<Transaction> transactions; // 關聯的交易記錄
		
		@Column(name = "identity_number", unique = true)
		private String identityNumber;//身分證字號
		
		@Column(name = "first_name")
		private String firstName;
		
		@Column(name = "last_name")
		private String lastName;
		
		@Column(name = "gender")
		private String gender;
		
		@Column(name = "birth_date")
		private Date birthDate;
		
		@Column(name = "occupation")
		private String occupation;
		
		@Column(name = "hashed_password")
		private String hashedPassword;//使用者Hash密碼
		
		@Column(name = "password_salt")
		private String passwordSalt;//隨機鹽
		
		@Column(name = "email", unique = true)
		private String email;//電子郵件
		
		@Column(name = "role")
		private String role;//角色權限

}
