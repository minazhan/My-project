package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;

//Spring JPA，處理簡單 CRUD
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ //User: Entity, Integer: @Id 的型別

	
	//根據 email 查找用戶
    Optional<User> findByEmail(String email);
}
