package com.example.demo.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.TransactionDto;
import com.example.demo.model.dto.UserCert;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.TransactionService;

import jakarta.servlet.http.HttpSession;

/**
請求方法 URL 路徑         					  功能說明       請求參數                   回應
-----------------------------------------------------------------------------------------------------
GET    /rest/transaction          		  取得所有交易資料  無                       成功時返回所有房間的列表及成功訊息。
POST   /rest/transaction   				  新增交易資料     請求體包含 transactionDto  成功時返回成功訊息，無回傳資料。
DELETE /rest/transaction/{transactionId}  刪除指定資料     transactionId           成功時返回成功訊息，無回傳資料。
*/


@RestController
@RequestMapping("/rest/transaction")
public class TransactionRestController {
	
	@Autowired
	private TransactionService transactionService;
	
	//取得所有交易資料
	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getTransactions(HttpSession session){
		
	    // 從 Session 獲取 UserCert
	    UserCert userCert = (UserCert) session.getAttribute("userCert");
	    if (userCert == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(ApiResponse.error(401, "未登入，請先登入"));
	    }
	    
	    // 如果已登入，返回使用者名稱
        String userName = userCert.getFirstName();
        System.out.println("使用者名稱：" + userName);
        
	    // 使用 userId 查詢該使用者的交易資料
	    List<TransactionDto> transactionDtos = transactionService.getTransactionsByUserId(userCert.getUserId());
		//List<TransactionDto> transactionDtos = transactionService.getAllTransactions();
		
	    // 使用 Map 封裝返回數據
	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("userName", userName);
	    responseData.put("transactions", transactionDtos);
	    
	    String message = transactionDtos.isEmpty() ? "Transaction查無資料" : "Transaction查詢成功";
		return ResponseEntity.ok(ApiResponse.success(message,responseData));
	}
	
	//新增交易資料
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> addTransaction(@RequestBody TransactionDto transactionDto,HttpSession session){
	    
		// 從 Session 獲取 UserCert
	    UserCert userCert = (UserCert) session.getAttribute("userCert");
	    if (userCert == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(ApiResponse.error(401,"未登入，請先登入"));
	    }
		
		if (transactionDto == null) {
	        throw new IllegalArgumentException("TransactionDto cannot be null");
	    }
		
		// 將 userId 設置到 DTO 中
	    transactionDto.setUserId(userCert.getUserId());//確保交易資料和當前登入的使用者正確對應的方法
		
		// 調用 Service 保存交易
		transactionService.addTransaction(transactionDto);
		return ResponseEntity.ok(ApiResponse.success("Transaction 新增成功", null));
	}
	
	//刪除指定資料
	@DeleteMapping("/{transactionId}")
	public ResponseEntity<ApiResponse<Integer>> deleteTransaction(@PathVariable Integer transactionId){
		transactionService.deleteTransaction(transactionId);
		return ResponseEntity.ok(ApiResponse.success("Transaction 刪除成功",transactionId));
	}
	

}
