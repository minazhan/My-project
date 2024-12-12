package com.example.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
請求方法 URL 路徑         				功能說明       請求參數                   					回應
-----------------------------------------------------------------------------------------------------------------------------
GET    /rest/user/{userId}  	   取得指定使用者資料 userId (路徑參數，使用者 ID)                 	成功時返回指定使用者資料及成功訊息。
PUT    /rest/user/update/{userId}  更新指定使用者資料 userId (路徑參數，使用者 ID)，請求體包含 userDto 	成功時返回成功訊息，無回傳資料。
*/


@RestController
@RequestMapping("/rest/user")
public class UserInfoRestController {
	
	@Autowired
	private UserService userService;
	
	//取得單筆使用者資料("/{userId}")@PathVariable Integer userId,
	@GetMapping
	public ResponseEntity<ApiResponse<UserDto>> getUser(HttpSession session){
		
		// 從 Session 獲取 UserCert
	    UserCert userCert = (UserCert) session.getAttribute("userCert");
	    if (userCert == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(ApiResponse.error(401, "未登入，請先登入"));
	    }
		
	    // 如果已登入，返回使用者id
	    Integer userId =  userCert.getUserId();
		
		UserDto userDto = userService.getUserById(userId);
		return ResponseEntity.ok(ApiResponse.success("User 查詢單筆成功", userDto ));
	}
	
	//修改使用者("/{userId}")
	@PutMapping
	public  ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto userDto, HttpSession session){
		 // 確認是否登入
	    UserCert userCert = (UserCert) session.getAttribute("userCert");
	    if (userCert == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(ApiResponse.error(401, "未登入，請先登入"));
	    }
		
	    // 確認 userId 是否匹配，這是用在url參數
//	    if (!userCert.getUserId().equals(userId)) {
//	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//	                .body(ApiResponse.error(403, "您無權修改其他用戶的資料"));
//	    }
	    
	    Integer userId = userCert.getUserId();
	    
	    //執行更新
		userService.updateUser(userId, userDto);
		return ResponseEntity.ok(ApiResponse.success("User 修改成功", userDto));
	}
	
}
