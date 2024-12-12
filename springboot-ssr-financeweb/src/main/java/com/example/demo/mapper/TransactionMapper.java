package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.TransactionDto;
import com.example.demo.model.entity.Transaction;

//主要用於簡化屬性之間的相互映射，以便在服務層與控制器之間輕鬆傳遞數據
@Component
public class TransactionMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public TransactionDto toDto(Transaction transaction) {
		// Entity 轉 DTO
		return modelMapper.map(transaction, TransactionDto.class);
	}
	
	public Transaction toEntity(TransactionDto transactionDto) {
		// DTO 轉 Entity
		return modelMapper.map(transactionDto, Transaction.class);
	}
}
