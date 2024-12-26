package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.UserRiskResponseDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserRiskResponseService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

/**
 * Method URI            功能
 * --------------------------------------------------------------------
 * GET    /admin/users                查詢所有使用者(多筆)
 * GET    /admin/user/{userId}        查詢指定使用者(單筆)
 * POST   /admin/user                 新增使用者
 * POST   /admin/user/update/{userId} 完整修改使用者
 * GET    /admin/user/delete/{userId} 刪除使用者
 * 
 * GET    /admin/user/detail/{userId} 顯示用戶明細(JSP 作為部分頁面（為 Modal 內容）)
 * --------------------------------------------------------------------
 * */


@Controller
@RequestMapping(value = {"/admin/user","/admin/users"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRiskResponseService userRiskResponseService;
	
	//查詢所有使用者
	@GetMapping
	public String getUsers(Model model,@ModelAttribute UserDto userDto) {
		List<UserDto> userDtos = userService.getAllUsers();
			//List<UserDto> userDtos = new ArrayList<>();
			//用於檢查資料有沒有送到jsp裡
			//userDtos.add(new UserDto(1, "小明",  "王", "男", new Date(),"Engineer","john.doe@example.com" ));
	        //userDtos.add(new UserDto(2, "小芳", "陳", "女", new Date(),"Designer", "jane.smith@example.com"));
	        // 添加日誌檢查
	        System.out.println("UserDtos size: " + userDtos.size());
        model.addAttribute("userDtos", userDtos);
		return "user/user";
	}
	
	
	//新增使用者(後台人員填寫)
	@PostMapping
	public String addUser(@Valid @ModelAttribute UserDto userDto,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			//如果有驗證錯誤，這行代碼會將 roomDtos（所有房間的列表）添加到 model 中
			model.addAttribute("userDtos",userService.getAllUsers());
			//要連動到註冊頁面return "user/user"; //會自動將錯誤訊息傳給 jsp
		}
		userService.addUser(userDto);
		return "redirect:/admin/users"; //重導到 /users 頁面
	}
	
	//刪除使用者(由後台)
	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		userService.deleteUser(userId);
		return "redirect:/admin/users"; // 重導到 /users 頁面
	}
	
	//修改使用者資料(由使用者修改)，把資料帶到修改頁面
	@GetMapping("/{userId}")
	public String getUser(@PathVariable Integer userId, Model model) {
		UserDto userDto = userService.getUserById(userId);
		model.addAttribute("userDto", userDto);
		//要連動到使用者修改頁面
		return "user/user_update";
	}
	
	//修改頁面
	@PostMapping("/update/{userId}")
	public String updateUser(@PathVariable Integer userId, @Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) { // 若有錯誤發生
			model.addAttribute("userDto", userDto); // ◇◇◇重要 將原本的 userDto 回傳
			return "user/user_update"; // 會自動將錯誤訊息傳給 jsp
		}
		userService.updateUser(userId, userDto);
		return "redirect:/admin/users"; // 重導到 /users 頁面
	}
	
	//處理錯誤
//	@ExceptionHandler({UserException.class})
//	public String handleUserException(UserException e, Model model) {
//		model.addAttribute("message", e.getMessage());
//		return "error";//將錯誤訊息傳給error.jsp
//	}

	
	@GetMapping("/detail/{userId}")
    public String getUserDetail(@PathVariable Integer userId, Model model) {
        // 模擬查詢用戶數據
        UserDto user = userService.getUserById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        //查詢子表數據
        //拋出了 UserNotFoundException，程序會進入 catch 塊進行處理
        //因為我在UserRiskResponseService，會拋出UserNotFoundException
        UserRiskResponseDto userResponse;
        try {
            userResponse = userRiskResponseService.getUserById(userId);
        } catch (UserNotFoundException e) {
            userResponse = new UserRiskResponseDto();
            userResponse.setRiskType("無風險評估");
            userResponse.setTotalScore(0);
        }
        
        // 將數據放入模型，供 JSP 使用
        model.addAttribute("user", user);
        model.addAttribute("userResponse", userResponse);

        // 返回 JSP 視圖名稱
        return "user/userDetailFragment"; // 對應 /WEB-INF/views/userDetailFragment.jsp
    }

}
