package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.OccupationService;
import com.example.demo.service.RiskTypeService;

@Controller
@RequestMapping("/admin")
public class ChartController {

	@Autowired
    private OccupationService occupationService;
	
	@Autowired
	private RiskTypeService riskTypeService;
	
	@GetMapping("/charts")
	public String dataChartsPage(Model model) {
		// 獲取職業統計數據
        Map<String, Long> occupationStats = occupationService.getOccupationStatistics();
        Map<String, Long> riskTypeStatistics = riskTypeService.getRiskTypeStatistics();
        
        model.addAttribute("occupationStats", occupationStats);
        model.addAttribute("riskTypeStatistics", riskTypeStatistics);
        return "user/chart"; //返回 JSP 頁面
	}
}
