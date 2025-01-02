package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.OccupationRepository;
import com.example.demo.service.OccupationService;

@Service
public class OccupationServiceImpl implements OccupationService{

	@Autowired
    private OccupationRepository occupationRepository;
	
	@Override
	public Map<String, Long> getOccupationStatistics() {
		
		//從repository獲取職業的統計資訊
		List<Object[]> results =  occupationRepository.findOccupationStatistics();
		
		// 定義職業標籤對應關係
		Map<String, String> occupationLabels = Map.of(
		    "1", "民意代表主管及經理人員",
		    "2", "專業人員",
		    "3", "技術員及助理專業人員",
		    "4", "事務支援人員",
		    "5", "服務及銷售工作人員",
		    "6", "農林漁牧業生產人員",
		    "7", "技藝有關工作人員",
		    "8", "機械設備操作及組裝人員",
		    "9", "基層技術工及勞力工",
		    "0", "軍人"
		);
		
		//轉成map，轉換後適合傳遞到前端
		Map<String, Long> occupationStatistics = new HashMap<>();
        	for (Object[] result : results) {
	            	String occupationCode = (String) result[0];
	            	Long count = (Long) result[1];
	            
		        //將職業代碼轉換成中文標籤，若沒有對應標籤，預設為 "其他"
		        String occupationLabel = occupationLabels.getOrDefault(occupationCode, "其他");
		            
	            	occupationStatistics.put(occupationLabel, count);
        	}
        return occupationStatistics;
	}

}
