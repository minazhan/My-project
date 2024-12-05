package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="question_id")
	private Integer questionId; //問題編號
	
	@Column(name="question_text")
	private String questionText; //問題內容
	
	@Column(name = "is_multi_select")
	private boolean isMultiSelect; // 是否為多選題
	
	//一個問題會有多個選項
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "question_option", //用來儲存每個問題對應選項
			joinColumns = @JoinColumn(name="question_id"),
			inverseJoinColumns = @JoinColumn(name="option_id")
			)
	private List<Option> options = new ArrayList<>(); // 每個問題對應的選項列表

}
