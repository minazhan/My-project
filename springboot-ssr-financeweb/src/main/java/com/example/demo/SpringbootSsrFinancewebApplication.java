package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.service.SurveyService;

@SpringBootApplication
@EnableScheduling //啟用定時任務功能
public class SpringbootSsrFinancewebApplication {
	
//	@Autowired
//    private SurveyService surveyService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSsrFinancewebApplication.class, args);
	}
	
	//Spring Boot 的介面，可以讓你在應用啟動時執行一些代碼
//	@Bean
//	public CommandLineRunner commandLineRunner() {
//        return args -> {
//            surveyService.createQuestionWithOptions();
//        };
//    }
}
