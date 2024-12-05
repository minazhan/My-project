package com.example.demo.model.dto;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRiskResponseDto {

	//表示這條錯誤訊息屬於 userRiskResponseDto 類
	@NotNull(message = "{userRiskResponseDto.userId.notNull}")
	private Integer userId;
	
	private Integer totalScore;
	
	private String riskType;
	
	//用於業務邏輯，例如用戶回答和分數計算
	@NotNull(message = "{userRiskResponseDto.userAnswers.notNull}")
	private Map<Integer, List<Integer>> userAnswers; //因為我的題目有多選，所以改成list
	
	//將userAnswers轉換為JSON
	//存儲 JSON 格式的用戶回答
    private String userAnswersJson;
}
