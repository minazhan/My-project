<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!-- 用於數字、日期格式化和國際化 -->

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!-- 用於字符串操作 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!-- Spring form 標籤庫 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Financial Survey Form</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Financial Survey Form</title>

<!-- 引入 Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">

<!-- 引入自訂的 CSS -->
<style>
body {
	background-color: #f0f2f5; /* 淡藍色背景 */
}

.card {
	margin-bottom: 20px; /* 每個問題之間的距離 */
	border-radius: 10px;
}

.card-header {
	font-weight: bold;
}

.navbar {
	position: sticky;
	top: 0;
	left: 0;
	width: 100%;
	background-color: #3e417d;
	color: #fff;
	padding: 5px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	z-index: 1000;
}

	    .navbar-brand{
        color:#ffe6e6;
    }
    
    	        .brand-logo{
            width: 150px;
            height: 60px;
            object-fit: contain;
        }
</style>
</head>
<body>
	<nav class="navbar">
		<a href="/" class="navbar-brand">
        	<img src="./img/finance3.PNG" alt="Finance Logo" class="brand-logo">
    	</a>
	</nav>
	<div class="container my-3">

		<!-- 表單標題 -->
		<div class="text-center mb-4">
			<h2>個人理財評估</h2>
			<p>各位使用者您好，問卷只需要很短時間完成~ 讓個人理財為您打造你專屬的理財系統吧！</p>
		</div>

		<!-- 表單開始 -->
		<!-- modelAttribute 來綁定這個 DTO，這樣前端頁面的數據就可以直接傳到後端的這個對象中 -->
		<form:form action="/form" method="post"
			modelAttribute="userRiskResponseDto">
			<!-- 隱藏欄位，包含 userId -->
    		<input type="hidden" name="userId" value="${userRiskResponseDto.userId}">
			<c:forEach var="question" items="${questions}">
				<div class="card">
					<div class="card-header">${question.questionText}</div>
					<div class="card-body">
						<!-- 根據 isMultiSelect 顯示不同的輸入類型 -->
						<c:if test="${question.multiSelect}">
							<!-- Question 實體中關聯的選項列表 -->
							<c:forEach var="option" items="${question.options}">
								<div class="form-check">
									<form:checkbox path="userAnswers[${question.questionId}]" class="form-check-input"
										value="${option.optionId}" /> 
									<label class="form-check-label"> ${option.text} </label>		
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${!question.multiSelect}">
							<c:forEach var="option" items="${question.options}">
								<div class="form-check">
									<form:radiobutton path="userAnswers[${question.questionId}]" class="form-check-input"
										value="${option.optionId}" /> 
									<label class="form-check-label"> ${option.text} </label>
										
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</c:forEach>

			<!-- 提交按鈕 -->
			<div class="text-center">
				<button type="submit" class="btn btn-primary">提交</button>
			</div>

			<!-- 表單標題 -->
			<div class="text-center mt-4">
				<p>謝謝您的耐心！恭喜恭喜</p>
			</div>

		</form:form>
	</div>

	<!-- 引入 Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQN6BujD0v8FIzt4GLT+a6NvkbW8llFHO5q7ljW5lw5H3e1e5d81z4ukx9Qq4Xv"
		crossorigin="anonymous">
		
	</script>


</body>
</html>