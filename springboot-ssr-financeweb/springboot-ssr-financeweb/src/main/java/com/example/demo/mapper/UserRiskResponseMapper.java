package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.UserRiskResponseDto;
import com.example.demo.model.entity.UserRiskResponse;


//主要用於簡化屬性之間的相互映射，以便在服務層與控制器之間輕鬆傳遞數據
@Component
public class UserRiskResponseMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserRiskResponseDto toDto(UserRiskResponse userRiskResponse) {
		// Entity 轉 DTO
		return modelMapper.map(userRiskResponse, UserRiskResponseDto.class);
	}
	
	public UserRiskResponse toEntity(UserRiskResponseDto userRiskResponseDto) {
		// DTO 轉 Entity
		return modelMapper.map(userRiskResponseDto, UserRiskResponse.class);
	}

}
