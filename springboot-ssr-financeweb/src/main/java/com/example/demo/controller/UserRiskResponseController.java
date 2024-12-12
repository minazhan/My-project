package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.UserRiskResponseDto;
import com.example.demo.model.entity.Question;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.SurveyService;
import com.example.demo.service.UserRiskResponseService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/form")
public class UserRiskResponseController {

	@Autowired
	private UserRiskResponseService userRiskResponseService;
	
	@Autowired
    private QuestionRepository questionRepository;
	
	@Autowired
	private SurveyService surveyService;
	
	// 顯示表單頁面
	//從 Session 中獲取 userId
	@GetMapping
	public String formPage(@RequestParam("userId") Integer userId,Model model) {
		
		UserRiskResponseDto userRiskResponseDto = new UserRiskResponseDto();
	    userRiskResponseDto.setUserId(userId);
		
		List<Question> questions = questionRepository.findAll();
		
		if (questions.isEmpty()) {
	        // 如果沒有題目，調用 createQuestionWithOptions 方法
	        surveyService.createQuestionWithOptions();
	        questions = questionRepository.findAll(); // 重新查詢題目
	    }
		
//		questions.forEach(q -> System.out.println("Question ID: " + q.getQuestionId() + ", isMultiSelect: " + q.isMultiSelect()));
		
		//在你的 JSP 表單中，這個對象可以用來捕捉用戶的輸入，並在提交表單時被 UserRiskResponseDto 這個類接收
		model.addAttribute("userRiskResponseDto",userRiskResponseDto);
		
		
		model.addAttribute("questions", questions);//從 QuestionRepository 中取得了所有的問題
		return "form";
	}
	
	// 處理表單提交
	//@ModelAttribute("userRiskResponseDto") 將表單中的數據與 UserRiskResponseDto 對象進行綁定
	@PostMapping
	public String handlePage(@Valid @ModelAttribute("userRiskResponseDto") UserRiskResponseDto userRiskResponseDto,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
    	    bindingResult.getAllErrors().forEach(error -> {
    	        System.out.println("驗證錯誤: " + error.getDefaultMessage());
    	    });
    	    List<Question> questions = questionRepository.findAll();
            model.addAttribute("questions", questions);
    	    return "form";
    	}

		userRiskResponseService.calculateUserRiskResponse(userRiskResponseDto);
		return "redirect:/login"; // 註冊成功後可以導向登入頁面或其他頁面
	}
}
