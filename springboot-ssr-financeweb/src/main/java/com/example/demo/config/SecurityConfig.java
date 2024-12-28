//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.example.demo.service.impl.UserDetailsServiceImpl;
//
//@Configuration
//@EnableMethodSecurity // 啟用方法級別的安全性控制
//public class SecurityConfig {
//	
//	@Autowired
//	private UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(auth -> auth
//            		.requestMatchers("/admin/**").hasRole("ADMIN")   // 限制 /admin/** 路徑需要 ADMIN 角色
//            		.requestMatchers("/index.html").hasRole("USER")  // 限制 /user/** 路徑需要 USER 角色
//            		.requestMatchers("/rest/user").hasRole("USER")   // 限制 /user/** 路徑需要 USER 角色
//            		.requestMatchers("/rest/transaction").hasRole("USER")   // 限制 /user/** 路徑需要 USER 角色
//            		.requestMatchers("/api/recommend").hasRole("USER")   // 限制 /user/** 路徑需要 USER 角色
//            		.anyRequest().permitAll()   // 其他所有請求需要登入
//            		)
//            
//            .csrf(csrf -> csrf.disable()) // 停用 CSRF（僅用於開發或測試環境）
//            
////           .formLogin(form -> form                      // 啟用表單登入
////                    .loginPage("/login")                     // 自訂登入頁面
////                    .usernameParameter("email") // 設定 email 為用戶名參數
////                    .passwordParameter("hashedPassword") // 密碼參數名稱
////                   .defaultSuccessUrl("/", true)        // 登入成功後跳轉的頁面
////                    .permitAll())                            // 允許所有人訪問登入頁面
//            
//            .logout(logout -> logout
//                    .logoutUrl("/logout") // 設置登出請求的路徑
//                    .logoutSuccessUrl("/") // 登出成功後跳轉到首頁
//                    .invalidateHttpSession(true) // 清除 HttpSession
//                    .permitAll()); // 允許所有人訪問登出功能
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // 使用 Bcrypt 進行密碼加密
//    }
//    
//    // Spring Security 處理登入邏輯
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsServiceImpl); // 使用自訂的 UserDetailsServiceImpl
//        authProvider.setPasswordEncoder(passwordEncoder());         // 設定密碼編碼器
//        return authProvider;
//    }
//
//    // Spring Security 處理身份驗證的核心類別
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager(); // 註冊 AuthenticationManager
//    }
//}
