package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Hash;
import com.example.demo.util.PasswordUtil;

//憑證服務
@Service
public class CertService {
	
	@Autowired
	private UserRepository userRepository;//要使用findByEmail方法
	
	//private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //BCrypt 編碼器
	
	public UserCert getCert(String email, String rawPassword){
		// 1. 查詢是否有此使用者
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
//            // 2. 比對密碼
//            String passwordHash=Hash.getHash(password, user.getPasswordSalt());
//            if(!passwordHash.equals(user.getHashedPassword())) {
//            	// 如果密碼不正確，丟出一個適當的例外
//            	throw new RuntimeException("密碼不正確，驗證失敗");
//            }
            
//         // 2. 判斷密碼存儲方式
//            boolean isBCrypt = user.getHashedPassword().startsWith("$2a$");
//
//            if (isBCrypt) {
//                // 使用 BCrypt 驗證
//                if (!passwordEncoder.matches(password, user.getHashedPassword())) {
//                    throw new RuntimeException("密碼不正確，驗證失敗");
//                }
//            } else {
//                // 使用自定義 hash 驗證（舊密碼）
//                String passwordHash = Hash.getHash(password, user.getPasswordSalt());
//                if (!passwordHash.equals(user.getHashedPassword())) {
//                    throw new RuntimeException("密碼不正確，驗證失敗");
//                }
//
//                // 如果舊密碼驗證成功，自動升級為 BCrypt
//                String bcryptHashedPassword = passwordEncoder.encode(password);
//                user.setHashedPassword(bcryptHashedPassword);
//                userRepository.save(user); // 保存到數據庫
//            }
            
            // 2. 驗證密碼
            boolean isValid = PasswordUtil.matches(rawPassword, user.getHashedPassword());
            if (!isValid) {
                throw new RuntimeException("密碼不正確，驗證失敗");
            }
            
            
            
            
            // 3. 簽發憑證
            UserCert userCert=new UserCert(user.getUserId(), user.getEmail(),user.getFirstName(),user.getRole());
            return userCert;
        	}else {
	            // 找不到使用者時，應該丟出一個例外或者返回 null
	            throw new RuntimeException("找不到使用者，驗證失敗");
        	}	
	}
}
