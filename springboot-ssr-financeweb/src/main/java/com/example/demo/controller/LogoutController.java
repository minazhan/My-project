package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    
	public String logout(HttpSession session) {
        session.invalidate(); // 清除所有 Session 數據
        return "redirect:/"; // 跳轉回登入頁面
    }

}
