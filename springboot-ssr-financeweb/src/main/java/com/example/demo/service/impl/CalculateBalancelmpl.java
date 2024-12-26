package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.CalculateBalance;

@Service
public class CalculateBalancelmpl implements CalculateBalance{
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public BigDecimal calculateBalance(Integer userId) {
		
		LocalDate now = LocalDate.now();
	    int currentMonth = now.getMonthValue();
	    int currentYear = now.getYear();

	    List<Transaction> transactions = transactionRepository.findByUserIdAndDate(userId, currentMonth, currentYear);

	    BigDecimal totalIncome = transactions.stream()
	        .filter(t -> "income".equals(t.getTransactionType()))
	        .map(t -> BigDecimal.valueOf(t.getExpense()))  // 將 Float 轉為 BigDecimal
	        .reduce(BigDecimal.ZERO, BigDecimal::add);

	    BigDecimal totalExpense = transactions.stream()
	        .filter(t -> "expense".equals(t.getTransactionType()))
	        .map(t -> BigDecimal.valueOf(t.getExpense()))
	        .reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalIncome.subtract(totalExpense);
	}

}
