package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.StockCodeDto;
import com.example.demo.model.dto.StockDto;
import com.example.demo.model.dto.TransactionDto;
import com.example.demo.model.entity.StockCodeEntity;
import com.example.demo.model.entity.StockEntity;
import com.example.demo.model.entity.Transaction;

//主要用於簡化屬性之間的相互映射，以便在服務層與控制器之間輕鬆傳遞數據
@Component
public class StockEntityMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public StockDto toDto(StockEntity stockEntity) {
		// Entity 轉 DTO
		return modelMapper.map(stockEntity, StockDto.class);
	}
	
	public StockEntity toEntity(StockDto stockDto) {
		// DTO 轉 Entity
		return modelMapper.map(stockDto, StockEntity.class);
	}
}
