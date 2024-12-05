//package com.example.demo.controller.rest;
//
//import java.util.Date;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/register")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
//public class UserRestController {
//
//	//抓取表單資料
//	@PostMapping
//	public String registerPage(@RequestParam("lastname") String lastname,
//								@RequestParam("firstname") String firstname,
//								@RequestParam("gender") String gender,
//								@RequestParam("occupation") String occupation,
//								@RequestParam("identityNumber") String identityNumber,
//								@RequestParam("email") String email,
//								@RequestParam("password") String password,
//								@RequestParam("birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate) {
//		System.out.println("註冊用戶名: " + lastname);
//        System.out.println("電子郵件: " + email);
//		
//		return "成功";
//	}
//	
//}
