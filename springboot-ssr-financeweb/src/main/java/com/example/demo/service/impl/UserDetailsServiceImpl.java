package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

//UserDetailsService，為spring security的內建介面
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//根據 email 查詢用戶
		User user = userRepository.findByEmail(email)
		.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		
		System.out.println("User Role: " + user.getRole());
		System.out.println("User Password: " + user.getHashedPassword());
		
		//返回 UserDetails 實例（可使用自訂的 UserDetails）
		return org.springframework.security.core.userdetails.User.builder() //使用 email 作為用戶名
				.username(user.getEmail()) //設置加密的密碼
				.password(user.getHashedPassword()) //設置用戶角色
				.roles(user.getRole().startsWith("ROLE_") ? user.getRole().substring(5) : user.getRole()) // 去掉 ROLE_ 前綴
				.build();
		
		
	}

}
