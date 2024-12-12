package com.example.demo.exception;

//用於User已存在時拋出
public class UserAlreadyExistsException extends UserException{

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
