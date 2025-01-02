package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserRegistrationDto;
import com.example.demo.service.CertService;
import com.example.demo.service.EmailService;
import com.example.demo.service.PasswordResetService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private CertService certService;
	
	//從登入頁觸發忘記密碼
	@Autowired
    	private PasswordResetService passwordResetService;
	
	@Autowired
    	private EmailService emailService;
	
	
	//顯示登入頁面
	@GetMapping
	public String loginPage() {
        return "login"; //導向註冊頁面 JSP
	}
	
	//檢查登入資訊
	@PostMapping
	public String checkLogin(@RequestParam String email, @RequestParam String hashedPassword, HttpSession session, HttpServletRequest req, Model model) {
		//透過 userService 來確認登入
		//驗證帳密並取得憑證
		UserCert userCert = null;
		
		try {
			//調用業務邏輯層，通過email和hashedPassword獲取驗證結果
			userCert = certService.getCert(email, hashedPassword);
			
	        //UserCert 包含角色信息
	        String role = userCert.getRole(); //從 UserCert 中獲取角色
	        session.setAttribute("role", role); //將角色保存到 Session
	        
	        //UserCert 包含姓名信息，用於顯示首頁名字
	        String firstName = userCert.getFirstName();
	        //model.addAttribute("firstName", firstName); //傳遞用戶名到 JSP
	        session.setAttribute("firstName", firstName);
	        
	        session.setAttribute("userCert", userCert);// 放憑證
	        session.setAttribute("locale", req.getLocale());//取得客戶端所在地 例如: zh_TW
		} catch (Exception e) {
			model.addAttribute("errorMessage", "帳號或密碼錯誤，請重新輸入！");
		    return "login"; // 回到登入頁面並顯示錯誤
			
		}
		System.out.println("Login successful, redirecting to /admin/users");
	
		// 打印 session ID 到控制台
	    	System.out.println("Session ID: " + session.getId());
		
		return "redirect:/"; //登入成功後到首頁，目前還沒做首頁，所以先放後台頁面
	}

}
