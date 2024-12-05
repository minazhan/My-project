package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`option`") //在 MySQL 中，option 是一個保留字，用於描述查詢選項，因此不能直接用作表名，除非用反引號（``）將它包住
public class Option {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private Integer optionId; //選項編號（主鍵）

	private String text; //選項描述
	
	private Integer score; //選項分數
		
	//option_id和question是多對一的關係
//	@ManyToOne
//	@JoinColumn(name = "question_id") //如果不想要有關聯表，可以寫這行，且@OneToMany(Question表)不能執行，因為question本來就找不到questionid，除非有關聯表
//	private Question question; //關聯到的問題
	
	@ManyToMany(mappedBy = "options", fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>(); // 與 Question 的雙向關聯

}
