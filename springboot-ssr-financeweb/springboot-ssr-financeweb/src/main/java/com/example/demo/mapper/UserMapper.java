package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;

//主要用於簡化屬性之間的相互映射，以便在服務層與控制器之間輕鬆傳遞數據
@Component
public class UserMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto toDto(User user) {
		// Entity 轉 DTO
		return modelMapper.map(user, UserDto.class);
	}
	
	public User toEntity(UserDto userDto) {
		// DTO 轉 Entity 
		return modelMapper.map(userDto, User.class);
	}

}
