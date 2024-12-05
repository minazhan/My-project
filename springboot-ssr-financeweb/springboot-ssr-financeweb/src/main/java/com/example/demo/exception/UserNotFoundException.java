package com.example.demo.exception;

//用於User未找到時拋出
public class UserNotFoundException extends UserException{

	public UserNotFoundException(String message) {
		super(message);
	}

}
