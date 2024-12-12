package com.example.demo.model.dto;

// 使用者憑證
// 登入成功之後會得到的憑證資料(只有 Getter)
public class UserCert {
	private Integer userId; // 使用者ID
	private String email; // 使用者email
	private String firstName; //使用者名稱

	
	public UserCert(Integer userId, String email,String firstName) {
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;

	}

	public Integer getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "UserCert [userId=" + userId + ", email=" + email + ", firstName=" + firstName + "]";
	}

	
}
