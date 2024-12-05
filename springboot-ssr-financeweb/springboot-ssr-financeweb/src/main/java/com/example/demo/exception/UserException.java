package com.example.demo.exception;

//用於User根例外
public class UserException extends RuntimeException{
	//建構子
	public UserException(String message) {
		super(message);
	}

}
