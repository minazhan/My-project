package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.RiskTypeRepository;
import com.example.demo.service.RiskTypeService;

@Service
public class RiskTypeServiceImpl implements RiskTypeService{
	
	@Autowired
	private RiskTypeRepository riskTypeRepository;

	@Override
	public Map<String, Long> getRiskTypeStatistics() {
		
		List<Object[]> results = riskTypeRepository.findRiskTypeStatistics();
		
		Map<String, Long> riskTypeStatistics = new HashMap<>();
        for (Object[] result : results) {
            String riskType = (String) result[0];
            Long count = (Long) result[1];
            riskTypeStatistics.put(riskType, count);
        }
		return riskTypeStatistics;
	}

}
