package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.EmailService;
import com.example.demo.service.PasswordResetService;

@Controller
public class ForgotPasswordController {

	 @Autowired
	 private EmailService emailService;

	@Autowired
	private PasswordResetService passwordResetService;

	//顯示忘記密碼頁面
	@GetMapping("/forgot-password")
	public String showForgotPasswordPage() {
		return "forgotPassword"; // 返回 JSP 頁面 (forgotPassword.jsp)
	}

	//處理忘記密碼請求
	@PostMapping("/forgot-password")
	public String handleForgotPassword(@RequestParam("email") String email, Model model) {
		try {
	            //生成重設密碼 Token
	            String resetToken = passwordResetService.generateResetToken(email);

	            //發送帶有 Token 的重設密碼連結到用戶郵箱
	            String resetLink = "http://localhost:8081/reset-password?token=" + resetToken;
	            String subject = "重設密碼請求";
	            String body = "您好，\n\n請點擊以下連結以重設您的密碼：\n" + resetLink +
	                    "\n此連結將在 30 分鐘後過期。";
	            emailService.sendEmail(email, subject, body);

	            model.addAttribute("message", "重設密碼連結已成功發送到您的信箱！");
	        } catch (RuntimeException e) {
	            model.addAttribute("error", e.getMessage());
	        }
	        return "forgotPassword"; //返回 JSP 頁面
	    }

	    //顯示重設密碼頁面
	    @GetMapping("/reset-password")
	    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
	        //驗證 token 是否有效
	        if (passwordResetService.validateToken(token)) {
	            model.addAttribute("token", token); //傳遞 token 到 JSP
	            return "resetPassword"; //返回重設密碼頁面 JSP
	        } else {
	            model.addAttribute("error", "無效或過期的重設密碼連結！");
	            return "forgotPassword"; //返回忘記密碼頁面
	        }
	    }

	    //處理重設密碼請求
	    @PostMapping("/reset-password")
	    public String handleResetPassword(
	            @RequestParam("token") String token,
	            @RequestParam("newPassword") String newPassword,
	            @RequestParam("confirmPassword") String confirmPassword,
	            Model model) {
	        if (!newPassword.equals(confirmPassword)) {
	            model.addAttribute("error", "密碼和確認密碼不一致！");
	            return "resetPassword";
	        }
	        try {
	            // 更新用戶密碼
	            passwordResetService.resetPassword(token, newPassword);
	            model.addAttribute("message", "密碼重設成功！請使用新密碼登入。");
	            return "passwordResetSuccess"; //重設成功頁面
	        } catch (RuntimeException e) {
	            model.addAttribute("error", e.getMessage());
	            return "resetPassword"; //返回重設密碼頁面
	        }
	    }
}
