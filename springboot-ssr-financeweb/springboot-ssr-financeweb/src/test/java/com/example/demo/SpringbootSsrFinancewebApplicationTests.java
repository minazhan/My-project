package com.example.demo;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;

@SpringBootTest
class SpringbootSsrFinancewebApplicationTests {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	void contextLoads() {
		
		//entity
//		User user= new User(101,"a123456789","王","小明","男生",Date.valueOf("2024-11-16"),"工程師","123","234","234");
//		System.out.println("原始 user:" + user);

		//toDto 將 Entity 轉 dto
//		UserDto userDto =userMapper.toDto(user);
//		System.out.println("測試 toDto:" + userDto);
		
		//toEntity 將 DTO 轉 entity
//		user = userMapper.toEntity(userDto);
//		System.out.println("測試 toEntity:" + user);
		
		
		
	}

}
