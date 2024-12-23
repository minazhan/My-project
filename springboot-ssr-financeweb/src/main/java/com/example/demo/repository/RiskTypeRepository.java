package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.UserRiskResponse;

public interface RiskTypeRepository extends CrudRepository<UserRiskResponse,Integer>{

	@Query("SELECT r.riskType, COUNT(r) FROM UserRiskResponse r GROUP BY r.riskType")
	List<Object[]> findRiskTypeStatistics();
}
