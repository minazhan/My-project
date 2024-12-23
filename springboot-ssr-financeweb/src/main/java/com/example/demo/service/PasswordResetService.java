package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class PasswordResetService {

	 @Autowired
	    private UserRepository userRepository;

	    public String generateTemporaryPassword() {
	        // 生成8位隨機密碼
	        return UUID.randomUUID().toString().substring(0, 8);
	    }

	    public void updatePassword(String email, String temporaryPassword) {
	        // 從資料庫查找用戶
	    	User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("該信箱未註冊！"));
	        if (user == null) {
	            throw new RuntimeException("該信箱未註冊！");
	        }

	        // 加密臨時密碼
	        String encryptedPassword = new BCryptPasswordEncoder().encode(temporaryPassword);

	        // 更新用戶密碼
	        user.setHashedPassword(encryptedPassword);
	        userRepository.save(user);
	    }
}
