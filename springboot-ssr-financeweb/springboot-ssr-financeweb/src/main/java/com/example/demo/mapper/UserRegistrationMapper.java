package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.UserRegistrationDto;
import com.example.demo.model.entity.User;

//主要用於簡化屬性之間的相互映射，以便在服務層與控制器之間輕鬆傳遞數據
@Component
public class UserRegistrationMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserRegistrationDto toDto(User user) {
		// Entity 轉 DTO
		return modelMapper.map(user, UserRegistrationDto.class);
	}
	
	public User toEntity(UserRegistrationDto userRegistrationDto) {
		// DTO 轉 Entity 
		return modelMapper.map(userRegistrationDto, User.class);
	}

}
