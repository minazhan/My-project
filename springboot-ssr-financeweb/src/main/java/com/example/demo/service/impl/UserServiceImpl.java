package com.example.demo.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRegistrationMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.UserRegistrationDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;

//service實作
@Service
public class UserServiceImpl implements UserService{
	
	//會用到repository，選擇jpa
	@Autowired
	private UserRepository userRepository;
	
	//會用到mapper，轉換dto
	@Autowired
	private UserMapper userMapper;
	
	//用於註冊新增轉換
	@Autowired
	private UserRegistrationMapper userRegistrationMapper;
	
	//查詢所有使用者
	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(user -> userMapper.toDto(user))
				.collect(Collectors.toList());
	}
	
	//查詢單筆使用者
	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow( () -> new UserNotFoundException("找不到使用者: userId:"+userId) );
		
		return userMapper.toDto(user); //轉成dto給controller
	}

	//新增使用者
	@Override
	public void addUser(UserDto userDto) {
		//要判斷使用者是否存在 //透過userDto中的id，尋找資料庫有沒有此筆資料
		Optional<User> optUser = userRepository.findById(userDto.getUserId());
		if (optUser.isPresent()) {
			throw new UserNotFoundException("新增失敗: " + userDto.getUserId() + " 已存在");
		}
		
		User user = userMapper.toEntity(userDto);
		userRepository.save(user);
		
		//丟出UserException???
		
		
	}

	@Override
	public void updateUser(Integer userId, UserDto userDto) {
		//要判斷使用者是否存在
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isEmpty()) {
			throw new UserNotFoundException("修改失敗: " + userId + " 不存在");
		}
		
		//從資料庫取得實體
		User user = optUser.get();
		
		//dto映射到entity
	    if (userDto.getFirstName() != null) {
	        user.setFirstName(userDto.getFirstName());
	    }
	    if (userDto.getLastName() != null) {
	        user.setLastName(userDto.getLastName());
	    }
	    if (userDto.getGender() != null) {
	        user.setGender(userDto.getGender());
	    }
	    if (userDto.getOccupation() != null) {
	        user.setOccupation(userDto.getOccupation());
	    }
	    if (userDto.getEmail() != null) {
	        user.setEmail(userDto.getEmail());
	    }
		//user.setUserId(userId);
		
		//userDto.setUserId(userId);
		//User user = userMapper.toEntity(userDto);
		userRepository.save(user);
		
		//丟出UserException???
		
		
	}

	@Override
	public void deleteUser(Integer userId) {
		/*
		//要判斷使用者是否存在
		User user = userRepository.findById(userId)
				.orElseThrow( () -> new UserNotFoundException("找不到使用者: userId:"+userId) );
		userRepository.deleteById(userId);
		*/
		
		//另一種
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException("刪除失敗: " + userId + " 不存在");
		}
		userRepository.deleteById(userId);
		
		//丟出UserException???
		
		
	}

	@Override
	public User addRegistrationUser(UserRegistrationDto userRegistrationDto) {
		String salt = Hash.getSalt(); // 得到隨機鹽
		//把密碼加上鹽
		String newPasswordHash = Hash.getHash(userRegistrationDto.getHashedPassword(), salt); 
		Boolean action = false; // email 尚未驗證成功
		
		// 使用DTO資料來建立User實體物件
		User user = userRegistrationMapper.toEntity(userRegistrationDto);
		
		// 設置加密後的密碼和鹽到 User 實體中
	    user.setHashedPassword(newPasswordHash);
	    user.setPasswordSalt(salt); // 確保 User 實體中有 passwordSalt 這個屬性
		
		// 將使用者資料存入資料庫
		userRepository.save(user);
		
		//返回保存後的user對象
	    return user;

				
		//丟出UserException???
				
		
	}

}
