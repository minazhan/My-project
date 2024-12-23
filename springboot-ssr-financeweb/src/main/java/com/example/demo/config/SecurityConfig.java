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
            .securityMatcher("/**") // 匹配所有請求
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**").permitAll() // 公開訪問
                .anyRequest().authenticated() // 其他路徑需要身份驗證
            )
            .formLogin(login -> login
                .loginPage("/login") // 指定自訂登入頁面
                .defaultSuccessUrl("/home", true) // 登入成功跳轉首頁
                .failureUrl("/login?error") // 登入失敗跳轉登入頁面
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // 設定登出 URL
                .logoutSuccessUrl("/") // 登出後跳轉首頁
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用 Bcrypt 進行密碼加密
    }
}
