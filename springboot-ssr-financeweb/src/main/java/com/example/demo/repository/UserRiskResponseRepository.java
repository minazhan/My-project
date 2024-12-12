package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.UserRiskResponse;

//Spring JPA，處理簡單 CRUD
@Repository
public interface UserRiskResponseRepository extends JpaRepository<UserRiskResponse, Integer>{

}
