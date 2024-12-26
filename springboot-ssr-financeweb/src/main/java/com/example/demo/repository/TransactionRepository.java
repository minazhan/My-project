package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Transaction;

//Spring JPA，處理簡單 CRUD
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	List<Transaction> findByUserId(Integer userId);//根據 userId 查詢交易
	
	//用於篩選出
	@Query("SELECT t FROM Transaction t WHERE t.userId = :userId AND FUNCTION('MONTH', t.transactionDate) = :month AND FUNCTION('YEAR', t.transactionDate) = :year")
	List<Transaction> findByUserIdAndDate(@Param("userId") Integer userId, @Param("month") int month, @Param("year") int year);

}
