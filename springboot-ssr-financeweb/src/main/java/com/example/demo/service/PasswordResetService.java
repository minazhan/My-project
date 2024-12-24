package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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

    // 用於存儲 token 與 email 對應關係
    private Map<String, String> tokenStorage = new HashMap<>();

    // 用於存儲 token 過期時間
    private Map<String, LocalDateTime> tokenExpiry = new HashMap<>();

    // Token 過期時間（例如 30 分鐘）
    private static final int TOKEN_EXPIRY_MINUTES = 30;

    /**
     * 生成臨時密碼
     */
    public String generateTemporaryPassword() {
        // 生成 8 位隨機密碼
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * 更新用戶密碼為臨時密碼
     */
    public void updatePassword(String email, String temporaryPassword) {
        // 從資料庫查找用戶
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("該信箱未註冊！"));

        // 加密臨時密碼
        String encryptedPassword = new BCryptPasswordEncoder().encode(temporaryPassword);

        // 更新用戶密碼
        user.setHashedPassword(encryptedPassword);
        userRepository.save(user);
    }

    /**
     * 生成重設密碼的 Token
     */
    public String generateResetToken(String email) {
        // 生成唯一 token
        String token = UUID.randomUUID().toString();
        tokenStorage.put(token, email); // 將 token 與 email 關聯
        tokenExpiry.put(token, LocalDateTime.now().plusMinutes(TOKEN_EXPIRY_MINUTES)); // 設定過期時間
        return token;
    }

    /**
     * 驗證 token 是否有效
     */
    public boolean validateToken(String token) {
        // 檢查 token 是否存在
        if (!tokenStorage.containsKey(token)) {
            return false;
        }

        // 檢查 token 是否過期
        LocalDateTime expiryTime = tokenExpiry.get(token);
        if (expiryTime == null || LocalDateTime.now().isAfter(expiryTime)) {
            tokenStorage.remove(token); // 移除過期 token
            tokenExpiry.remove(token);
            return false;
        }

        return true;
    }

    /**
     * 重設用戶密碼
     */
    public void resetPassword(String token, String newPassword) {
        // 驗證 token 是否有效
        if (!validateToken(token)) {
            throw new RuntimeException("無效或過期的重設密碼連結！");
        }

        // 根據 token 獲取 email
        String email = tokenStorage.get(token);

        // 從資料庫查找用戶
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("找不到對應的用戶！"));

        // 加密新密碼
        String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);

        // 更新用戶密碼
        user.setHashedPassword(encryptedPassword);
        userRepository.save(user);

        // 清除已使用的 token
        tokenStorage.remove(token);
        tokenExpiry.remove(token);
    }
}
