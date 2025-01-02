package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.TransactionNotFoundException;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.dto.TransactionDto;
import com.example.demo.model.entity.Transaction;
import com.example.demo.model.entity.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	//會用到repository，選擇jpa
	@Autowired
	private TransactionRepository transactionRepository;
	
	//用於連動User表的user_id
	@Autowired
    	private UserRepository userRepository;
	
	//entity和dto轉換
	@Autowired
	private TransactionMapper transactionMapper;
	
	//新增交易明細(給使用者用)
	@Override
	public void addTransaction(TransactionDto transactionDto) {
		Optional<Transaction> optTransaction = transactionRepository.findById(transactionDto.getTransactionId()); //查看交易在資料庫是否存在
		System.out.println(optTransaction);
		if (optTransaction.isPresent()) {
			throw new TransactionNotFoundException("新增失敗: " + transactionDto.getTransactionId() + " 已存在");
		}
		
		//根據 userId 查詢對應的 User 實體
		Optional<User>  optUser = userRepository.findById(transactionDto.getUserId());
		User user = optUser.orElseThrow(() ->  new RuntimeException("新增失敗: " + transactionDto.getUserId() + "User 不存在"));
		
		
		//手動將 DTO 映射到實體	
		Transaction transaction = new Transaction();
		transaction.setUser(user); // 關聯 User
	        transaction.setTransactionDate(transactionDto.getTransactionDate());
	        transaction.setTransactionType(transactionDto.getTransactionType());
	        transaction.setExpense(transactionDto.getExpense());
	        transaction.setCategory(transactionDto.getCategory());
		
		//Transaction transaction = transactionMapper.toEntity(transactionDto); //把資料轉換entity
		
        transactionRepository.save(transaction); //存到資料庫
	}

	//擺在表格中的刪除按鈕(給使用者用)
	@Override
	public void deleteTransaction(Integer transactionId) {
		Optional<Transaction> optTransaction = transactionRepository.findById(transactionId);
		if(optTransaction.isEmpty()) {
			throw new TransactionNotFoundException("刪除失敗: " + transactionId + " 不存在");
		}
		transactionRepository.deleteById(transactionId);
	}

	//用於當日期作篩選時，會顯示在表格中(支出/收入)
	@Override
	public List<TransactionDto> getAllTransactions() {
		return transactionRepository.findAll()
				.stream()
				.map(transaction -> transactionMapper.toDto(transaction))
				.collect(Collectors.toList());
	}

	//查詢該使用者的所有交易
	@Override
	public List<TransactionDto> getTransactionsByUserId(Integer userId) {
		return transactionRepository.findByUserId(userId)
				.stream()
				.map(transaction -> transactionMapper.toDto(transaction))
				.collect(Collectors.toList());
	}

}
