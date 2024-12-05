package com.example.demo.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto { //包含敏感信息，用於接收前端傳遞的數據（例如註冊、更新資料）
	//把訊息集中在messages.properties，如果要更改就在messages.properties更改
		//@NotNull(message = "{roomDto.roomId.notNull}")
		
		//表示這條錯誤訊息屬於 userDto 類
//		@NotNull(message = "{userRegistrationDto.userId.notNull}")
//		private Integer userId;//使用者ID
		
		@NotBlank(message = "{userRegistrationDto.firstName.notNull}")
		private String firstName;
		
		@NotBlank(message = "{userRegistrationDto.lastName.notNull}")
		private String lastName;
		
		@NotBlank(message = "{userRegistrationDto.gender.notNull}")
		private String gender;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@NotNull(message = "{userRegistrationDto.birthDate.notNull}")
		private Date birthDate;
		
		@NotBlank(message = "{userRegistrationDto.occupation.notNull}")
		private String occupation;
		
		@NotBlank(message = "{userRegistrationDto.identityNumber.notNull}")
		private String identityNumber;//身分證字號
		
		@NotBlank(message = "{userRegistrationDto.hashedPassword.notNull}")
		private String hashedPassword;//使用者Hash密碼
		
//		@NotNull(message = "{userRegistrationDto.passwordSalt.notNull}")
//		private String passwordSalt;//隨機鹽
		
		@NotBlank(message = "{userRegistrationDto.email.notNull}")
		private String email;//電子郵件
}
