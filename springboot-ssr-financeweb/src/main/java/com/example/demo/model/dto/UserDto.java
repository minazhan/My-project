package com.example.demo.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto { //不包含敏感信息，用於返回給前端（例如用戶查詢、資料展示）
	
	//把訊息集中在messages.properties，如果要更改就在messages.properties更改
	//@NotNull(message = "{roomDto.roomId.notNull}")
	
	//表示這條錯誤訊息屬於 userDto 類
	@NotNull(message = "{userDto.userId.notNull}")
	private Integer userId;//使用者ID
	
	@NotNull(message = "{userDto.firstName.notNull}")
	private String firstName;
	
	@NotNull(message = "{userDto.lastName.notNull}")
	private String lastName;
	
	@NotNull(message = "{userDto.gender.notNull}")
	private String gender;
	
	@NotNull(message = "{userDto.birthDate.notNull}")
	private Date birthDate;
	
	@NotNull(message = "{userDto.occupation.notNull}")
	private String occupation;
	
	@NotNull(message = "{userDto.email.notNull}")
	private String email;//電子郵件
	
	@NotNull(message = "{userDto.role.notNull}")
	private String role;//角色權限

}
