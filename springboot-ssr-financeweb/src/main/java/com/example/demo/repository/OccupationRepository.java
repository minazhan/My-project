package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.User;

public interface OccupationRepository extends CrudRepository<User, Integer>{
	// 使用 JPQL 查詢，每個職業的數量統計
    	@Query("SELECT r.occupation, COUNT(r) FROM User r GROUP BY r.occupation")
    	List<Object[]> findOccupationStatistics(); //返回的是分組統計後的結果
}
