package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Hash;

//憑證服務
@Service
public class CertService {
	
	@Autowired
	private UserRepository userRepository;//要使用findByEmail方法
	
	public UserCert getCert(String email, String password){
		// 1. 查詢是否有此使用者
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 2. 比對密碼
            String passwordHash=Hash.getHash(password, user.getPasswordSalt());
            if(!passwordHash.equals(user.getHashedPassword())) {
            	// 如果密碼不正確，丟出一個適當的例外
            	throw new RuntimeException("密碼不正確，驗證失敗");
            }
            // 3. 簽發憑證
            UserCert userCert=new UserCert(user.getUserId(), user.getEmail(),user.getFirstName());
            return userCert;
        }else {
            // 找不到使用者時，應該丟出一個例外或者返回 null
            throw new RuntimeException("找不到使用者，驗證失敗");
        }	
	}
}
