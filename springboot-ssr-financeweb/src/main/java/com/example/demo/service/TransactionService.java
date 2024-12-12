package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.TransactionDto;

//業務邏輯層
public interface TransactionService {
	
	public void addTransaction(TransactionDto transactionDto); //新增交易明細(給使用者用)
	public void deleteTransaction(Integer transactionId); //擺在表格中的刪除按鈕(給使用者用)
	public List<TransactionDto> getAllTransactions(); //用於當日期作篩選時，會顯示在表格中(支出/收入)
	public List<TransactionDto> getTransactionsByUserId(Integer userId);//查詢該使用者的所有交易
	
	
	//更新先不做因為表格怕放不下，且在刪除更新也可以

}
