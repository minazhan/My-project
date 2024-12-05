package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.UserRegistrationDto;

@Controller
@RequestMapping("/")
public class MainPageController {
	
	// 顯示註冊頁面
	@GetMapping
	public String mainPage(Model model) {
		// 添加需要傳遞給視圖的屬性（如果需要）
        model.addAttribute("message", "歡迎來到我的網站！");
        return "main_page"; // 導向首頁 JSP（例如 main_page.jsp）
	}

}
