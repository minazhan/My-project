package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.UserRiskResponse;

//Spring JPA，處理簡單 CRUD
@Repository
public interface UserRiskResponseRepository extends JpaRepository<UserRiskResponse, Integer>{

	// 根據 userId 查詢對應的 riskType
    @Query("SELECT u.riskType FROM UserRiskResponse u WHERE u.userId = :userId")
    String findRiskTypeByUserId(@Param("userId") Integer userId);
}
