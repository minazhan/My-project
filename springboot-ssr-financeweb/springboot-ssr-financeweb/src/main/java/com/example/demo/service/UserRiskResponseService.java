package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.dto.UserRiskResponseDto;

//計算用戶的風險評估分數
public interface UserRiskResponseService {
	
	//計算總分
	public void calculateUserRiskResponse(UserRiskResponseDto userRiskResponseDto);
	public UserRiskResponseDto getUserById(Integer userId); //查詢單筆使用者問卷
	public void updateUser(Integer userId,UserRiskResponseDto userRiskResponseDto);//修改使用者表單
}
