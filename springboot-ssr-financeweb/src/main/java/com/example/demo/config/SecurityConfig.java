package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // 允許所有請求
            .csrf(csrf -> csrf.disable()) // 停用 CSRF（僅用於開發或測試環境）
            .logout(logout -> logout
                    .logoutUrl("/logout") // 設置登出請求的路徑
                    .logoutSuccessUrl("/") // 登出成功後跳轉到首頁
                    .invalidateHttpSession(true) // 清除 HttpSession
                    .permitAll()); // 允許所有人訪問登出功能
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用 Bcrypt 進行密碼加密
    }
}
