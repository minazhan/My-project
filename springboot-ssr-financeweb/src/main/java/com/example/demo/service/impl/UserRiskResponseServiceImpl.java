package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserRiskResponseMapper;
import com.example.demo.model.dto.UserRiskResponseDto;
import com.example.demo.model.entity.Option;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserRiskResponse;
import com.example.demo.repository.OptionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRiskResponseRepository;
import com.example.demo.service.UserRiskResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserRiskResponseServiceImpl implements UserRiskResponseService{

	//會用到repository，選擇jpa
	@Autowired
	private UserRiskResponseRepository userRiskResponseRepository;
	
	//會用到mapper，轉換dto
	@Autowired
	private UserRiskResponseMapper userRiskResponseMapper;
	
	@Autowired
	private OptionRepository optionRepository;
	
	private final ObjectMapper objectMapper = new ObjectMapper(); //使用 Jackson 來處理 JSON
	
	@Autowired
	private UserRepository userRepository;
	
	//新增表單計算總分、風險類型
	@Override
	public void calculateUserRiskResponse(UserRiskResponseDto userRiskResponseDto) {
		int totalScore = 0;
		
		//計算總分數
		Map<Integer, List<Integer>> userAnswers = userRiskResponseDto.getUserAnswers();
		for (Map.Entry<Integer, List<Integer>> entry : userAnswers.entrySet()) {
			List<Integer> optionIds = entry.getValue();
			for (Integer optionId : optionIds) {
			        Option selectedOption = optionRepository.findById(optionId).orElse(null);
			        if (selectedOption != null) {
			            totalScore += selectedOption.getScore(); // 累加選項的分數
			        }
		    	}
        	}
		
		//設置風險類型
	        String calculatedRiskType = determineRiskType(totalScore);
	        userRiskResponseDto.setTotalScore(totalScore); //設置到userRiskResponseDto
	        userRiskResponseDto.setRiskType(calculatedRiskType); //設置到userRiskResponseDto
        
	        //要判斷使用者是否存在 //透過userRiskResponseDto中的id，尋找資料庫有沒有此筆資料
	        Optional<UserRiskResponse> optUserRiskResponse =userRiskResponseRepository.findById(userRiskResponseDto.getUserId());
	        if(optUserRiskResponse.isPresent()) {
	        	throw new UserAlreadyExistsException("新增失敗: "+userRiskResponseDto.getUserId()+ " 已存在");
	        }
        
	        //將 userAnswers 轉換為 JSON 格式並設置到 DTO 中
	        try {
	            String userAnswersJson = objectMapper.writeValueAsString(userRiskResponseDto.getUserAnswers()); //把map轉成json
	            userRiskResponseDto.setUserAnswersJson(userAnswersJson);
	        } catch (Exception e) {
	            throw new RuntimeException("Error converting userAnswers to JSON", e);
	        }
        
	        //使用 Mapper 將 DTO 轉換為實體
	        //UserRiskResponse userRiskResponse = userRiskResponseMapper.toEntity(userRiskResponseDto);
	
	        //手動將 DTO 映射到實體
	        UserRiskResponse userRiskResponse = new UserRiskResponse();
	        
	        //將 DTO 的屬性逐一映射到實體中
	        userRiskResponse.setTotalScore(userRiskResponseDto.getTotalScore());
	        userRiskResponse.setRiskType(userRiskResponseDto.getRiskType());
	        userRiskResponse.setUserAnswersJson(userRiskResponseDto.getUserAnswersJson());
	        
	        //通過 userId 查詢 User 並設置到 UserRiskResponse
	        User user = userRepository.findById(userRiskResponseDto.getUserId())
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        userRiskResponse.setUser(user);
	        
	        //保存到資料庫
	        userRiskResponseRepository.save(userRiskResponse);
	        
		}

		//查詢單筆使用者問卷
		@Override
		public UserRiskResponseDto getUserById(Integer userId) {
			UserRiskResponse userRiskResponse = userRiskResponseRepository.findById(userId)
					.orElseThrow( () -> new UserNotFoundException("找不到使用者: userId:"+userId));
			return userRiskResponseMapper.toDto(userRiskResponse); //轉成dto給controller
		}

		//修改使用者表單
		@Override
		public void updateUser(Integer userId, UserRiskResponseDto userRiskResponseDto) {
			//要判斷使用者是否存在
			Optional<UserRiskResponse> optUserRiskResponse =userRiskResponseRepository.findById(userRiskResponseDto.getUserId());
			if(optUserRiskResponse.isEmpty()) {
				throw new UserNotFoundException("修改失敗: " + userId + " 不存在");
			}
			userRiskResponseDto.setUserId(userId);
			UserRiskResponse userRiskResponse = userRiskResponseMapper.toEntity(userRiskResponseDto);
			
			//保存到資料庫
	        userRiskResponseRepository.save(userRiskResponse);
		}
	
		//根據總分來判斷風險類型
		private String determineRiskType(int totalScore) {
		        if (totalScore <= 25) {
		            return "保守型";
		        } else if (totalScore <= 45) {
		            return "穩健型";
		        } else {
		            return "積極型";
		        }
    		}

}
