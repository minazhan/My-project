package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.UserRegistrationDto;
import com.example.demo.model.entity.User;

import jakarta.validation.Valid;

//業務邏輯層
public interface UserService {
	public List<UserDto> getAllUsers();//查詢所有使用者
	public UserDto getUserById(Integer userId);//查詢單筆使用者
	public void addUser(UserDto userDto);//新增使用者(後端)
	public void updateUser(Integer userId,UserDto userDto);//修改使用者
	public void deleteUser(Integer userId);//刪除使用者
	public User addRegistrationUser(UserRegistrationDto userRegistrationDto);//新增註冊使用者(加上hash)
}
