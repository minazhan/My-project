package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.TransactionDto;
import com.example.demo.service.TransactionService;

import jakarta.validation.Valid;

/**
請求方法 URL 路徑         						功能說明       請求參數                   回應
-----------------------------------------------------------------------------------------------------
GET    /transaction          		 		取得所有交易資料  無                       成功時返回所有房間的列表及成功訊息。
POST   /transaction   				 		新增交易資料     請求體包含 transactionDto  成功時返回成功訊息，無回傳資料。
GET    /transaction/delete/{transactionId}  刪除指定資料     transactionId           成功時返回成功訊息，無回傳資料。
*/


@Controller
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	//取得所有交易資料
	@GetMapping
	public String getTransactions(Model model,@ModelAttribute TransactionDto transactionDto){
		List<TransactionDto> transactionDtos = transactionService.getAllTransactions();
		model.addAttribute("transactionDtos" , transactionDtos);
		return "/transaction";
	}
	
	//新增交易資料
	@PostMapping
	public String addTransaction(@Valid @ModelAttribute TransactionDto transactionDto,BindingResult bindingResult,Model model){
		if(bindingResult.hasErrors()) {
			model.addAttribute("transactionDtos" , transactionService.getAllTransactions());
		}
		transactionService.addTransaction(transactionDto);
		return "redirect:/transaction";
	}
	
	//刪除指定資料
	@GetMapping("/delete/{transactionId}")
	public String deleteTransaction(@PathVariable("transactionId") Integer transactionId){
		transactionService.deleteTransaction(transactionId);
		return "redirect:/transaction";
	}
	

}
