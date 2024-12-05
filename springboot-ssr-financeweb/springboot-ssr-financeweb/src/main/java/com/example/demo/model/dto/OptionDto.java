package com.example.demo.model.dto;

import com.example.demo.model.entity.Question;

import lombok.Data;

@Data
public class OptionDto {

	private Integer optionId;
	private String text;
	private Integer score;

}
