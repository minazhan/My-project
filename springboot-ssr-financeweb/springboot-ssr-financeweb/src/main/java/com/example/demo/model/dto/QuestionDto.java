package com.example.demo.model.dto;

import java.util.List;

import com.example.demo.model.entity.Option;

import lombok.Data;

@Data
public class QuestionDto {
	
	private Integer questionId;
	private String questionText;
	private boolean isMultiSelect; // 添加 isMultiSelect 屬性
	private List<Option> options;

}
