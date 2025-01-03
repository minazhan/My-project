package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Option;
import com.example.demo.model.entity.Question;
import com.example.demo.repository.OptionRepository;
import com.example.demo.repository.QuestionRepository;

import jakarta.transaction.Transactional;


@Service
public class SurveyService {
	
	@Autowired
	private QuestionRepository questionRepository; //操作資料庫
	
	@Transactional
	public void createQuestionWithOptions() {
		
		//準備問題和選項資料結構
		Map<String, List<String>> questionData = new LinkedHashMap<>();
		
		//定義第一題及其選項
		questionData.put("1. 您的年齡是？", Arrays.asList(
				"A. 65 歲以上",
				"B. 56 歲~64 歲",
				"C. 46 歲~55 歲",
				"D. 36 歲~45 歲",
				"E. 19 歲~35 歲",
				"F. 18 歲以下"
		));
		
		//定義第二題及其選項
		questionData.put("2. 曾經使用過之投資理財工具種類？【可複選】", Arrays.asList(
				"A. 無",
				"B. 債券類型基金或保單連結債券",
				"C. 股票型基金或保單連結股票",
				"D. 股票",
				"E. 外匯交易",
				"F. 期貨、選擇權等衍生性金融商品"
		));
		
		//定義第三題及其選項
		questionData.put("3. 投資與債券類型相關商品之理財工具經驗？【單選題】", Arrays.asList(
				"A. 無經驗",
				"B. 1 年以下",
				"C. 1 年（含）~ 3 年",
				"D. 3 年（含）~ 5 年",
				"E. 5 年（含）以上"
		));	
		
		//定義第四題及其選項
		questionData.put("4. 投資與其他類型相關商品之理財工具經驗？【單選題】", Arrays.asList(
				"A. 無經驗",
				"B. 1 年以下",
				"C. 1 年（含）~ 3 年",
				"D. 3 年（含）~ 5 年",
				"E. 5 年（含）以上"
		));	
		
		//定義第五題及其選項
		questionData.put("5. 下列何者最符合您對投資理財工具的理解？【單選題】", Arrays.asList(
				"A. 對投資理財工具不熟悉，但有興趣了解",
				"B. 瞭解基本知識",
				"C. 瞭解分散投資及資產配置的重要性",
				"D. 對投資風險有進一步認識",
				"E. 非常熟悉大部分投資工具及風險"
		));	
		
		//定義第六題及其選項
		questionData.put("6. 每年可用於購買投資理財工具之金額(新台幣)？【單選題】", Arrays.asList(
				"A. 未滿 50 萬",
				"B. 50 萬以上~未滿 100 萬",
				"C. 100 萬以上~未滿 300 萬",
				"D. 300 萬以上"
		));	
		
		//定義第七題及其選項
		questionData.put("7. 請問您的備用金（現金及存款）相當於您幾個月的生活開銷？【單選題】", Arrays.asList(
				"A. 無備用金或無須負擔生活開銷",
				"B. 3 個月以下",
				"C. 超過 3 個月未達 6 個月",
				"D. 超過 6 個月未達 1 年",
				"E. 超過 1 年",
				"F. 超過 3 年以上"
		));	
		
		//定義第八題及其選項
		questionData.put("8. 請問您購買投資型保單連結外幣計價投資標的，每年可承受的價格損失（含匯率風險）？【單選題】", Arrays.asList(
				"A. 無法接受虧損",
				"B. -5%",
				"C. -10%",
				"D. -15%",
				"E. -20%"
		));	
		
		//定義第九題及其選項
		questionData.put("9. 請問您購買投資型保單所連結投資標的，在達到預計投資期間時（例如 3 年、5 年），可承受的價格損失（含匯率風險）？【單選題】", Arrays.asList(
				"A. 無法接受虧損",
				"B. -5%",
				"C. -10%",
				"D. -15%",
				"E. -20%"
		));	
		
		//定義第十題及其選項
		questionData.put("10. 您的投資回報期望？【單選題】", Arrays.asList(
				"A. 避免資產損失",
				"B. 資產每年穩定成長",
				"C. 資產短期快速成長"
		));	
		
		//定義第十一題及其選項
		questionData.put("11. 就長期投資而言，您期望每年平均投資報酬率？【單選題】", Arrays.asList(
				"A. 1%（含）~5%",
				"B. 5%（含）~10%",
				"C. 10%（含）~15%",
				"D. 15%（含）~20%"
		));	
		
		//定義第十二題及其選項
		questionData.put("12. 當投資發生虧損或達到停損點時會採取的處理方式？【單選題】", Arrays.asList(
				"A. 立即賣出",
				"B. 先賣出一半",
				"C. 虧損未達 6 個月就賣掉",
				"D. 虧損已經 6 個月以上才考慮出售",
				"E. 持有 1 年以上",
				"F. 持有至回本"
		));	
		
        //遍歷每個問題並創建 Question 和 Option
	int questionIndex = 1; // 用於計算哪一題是多選
        for (Map.Entry<String, List<String>> entry : questionData.entrySet()) {
            //創建問題
            Question question = new Question();
            question.setQuestionText(entry.getKey());

            //決定是否為多選題
            //在這裡我們將第二題（index 為 2）設為多選題
            if (questionIndex == 2) {
                question.setMultiSelect(true); //第二題設為多選
            } else {
                question.setMultiSelect(false); //其他題目設為單選
            }
            
            //創建選項並加入問題
            List<Option> options = new ArrayList<>();
            int score = 1; //假設每個選項的分數按順序增加
            for (String optionText : entry.getValue()) {
                Option option = new Option();
                option.setText(optionText);
                option.setScore(score++); //每個選項的分數遞增
                options.add(option);
            }

            //將選項加入問題
            question.setOptions(options);

            //保存問題，JPA 會自動保存選項及更新中間表
            questionRepository.save(question);
            
            questionIndex++;//將題目編號增加
        }
	}
}
