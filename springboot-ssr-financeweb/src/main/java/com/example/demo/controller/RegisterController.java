package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.UserRegistrationDto;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	// 顯示註冊頁面
	@GetMapping
	public String registerPage(Model model) {
		model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "register"; // 導向註冊頁面 JSP（例如 register.jsp）
	}
	
	// 處理註冊提交
    @PostMapping
    public String handleRegister(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    	    bindingResult.getAllErrors().forEach(error -> {
    	        System.out.println("驗證錯誤: " + error.getDefaultMessage());
    	    });
    	    return "register";
    	}

    	//會取決於UserService方法有無回傳值，如果是void就不能使用
    	User user = userService.addRegistrationUser(userRegistrationDto);
    	System.out.println("userId: " + user.getUserId());
    	
    	//如果role為ADMIN，則直接跳轉到登入頁面
    	if ("ADMIN".equals(userRegistrationDto.getRole())) {
    		return "redirect:/login";
		}
    	return "redirect:/form?userId=" + user.getUserId(); // 註冊成功後可以導向表單頁面或其他頁面
    }
    
//    if (bindingResult.hasErrors()) {
//        // 如果有驗證錯誤，返回註冊頁面
//    	System.out.println("有錯誤");
//        return "register";
//    }
    
    

}
