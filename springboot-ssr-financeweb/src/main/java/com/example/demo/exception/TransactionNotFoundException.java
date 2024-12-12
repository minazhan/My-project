package com.example.demo.exception;

public class TransactionNotFoundException extends RuntimeException{
	//建構子
	public TransactionNotFoundException(String message) {
		super(message);
	}
	
	
}
