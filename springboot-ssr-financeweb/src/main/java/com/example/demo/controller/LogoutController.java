package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    
	//明確指定這個方法處理 HTTP GET 請求
	@GetMapping
	public String logout(HttpSession session) {
        session.invalidate(); //清除所有 Session 數據
        return "redirect:/"; //跳轉回登入頁面
    }

}
