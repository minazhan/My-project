<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 用於數字、日期格式化和國際化 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 用於字符串操作 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>

<!-- 引入bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">


<style>
	.container-fluid {
		max-width: 800px;
		margin: 0 auto;
		padding-top: 100px; /* 確保內容不會被固定的 Navbar 覆蓋 */
		flex: 1;
	}
	
	.navbar {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		background-color: #cad6e9;
		color: #fff;
		padding: 15px 20px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		z-index: 1000; /* 確保 navbar 在最上層 */
	}
	
	html, body {
		height: 100%;
		margin: 0;
		padding: 0;
		display: flex;
		/* align-items: center; */
		justify-content: center;
		background-color: #f0f2f5; /* 淡藍色背景 */
		/* 或其他你想設定的背景顏色 */
		flex-direction: column;
		/* 讓導航列在上方，內容居中 */
		padding-bottom: 60px; /* 確保內容不被 Footer 覆蓋，值可以根據 Footer 的高度調整 */
	}
	
	.login-container {
		min-height: calc(100vh - 60px);
		/* 100vh 減去導航欄的高度 */
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.btn-outline-login {
		color: #5f6368;
		/* 灰色字體 */
		border: 1px solid #5f6368;
		/* 灰色邊框 */
		background-color: transparent;
		/* 背景透明 */
	}
	
	.btn-outline-login:hover {
		background-color: #f1f1f1;
		/* 滑鼠移入時改變背景顏色 */
	}
	
	.btn-outline-forgot {
		color: #f05757;
		/* 紅色字體 */
		border: 1px solid #f05757;
		/* 紅色邊框 */
		background-color: transparent;
		/* 背景透明 */
	}
	
	.btn-outline-forgot:hover {
		background-color: #ffe6e6;
		/* 滑鼠移入時改變背景顏色 */
	}
	
	.footer {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%; /* 使 Footer 寬度佔滿整個視窗 */
		background-color: #cad6e9;
		color: #000000;
		padding: 15px 15px;
		text-align: center;
		font-size: 0.9em;
		z-index: 1000; /* 保證 Footer 在最上層 */
	}
	
	.footer-links {
		margin-top: 10px;
		display: flex;
		justify-content: center;
		gap: 15px;
	}
	
	.footer-links a {
		color: hsl(0, 0%, 0%);
		text-decoration: none;
		font-size: 0.9em;
	}
	
	.footer-links a:hover {
		text-decoration: underline;
	}
</style>

</head>

<body>

	<div class="container-fluid">
		<nav class="navbar">
			<a class="navbar-brand" href="#">XX理財</a>
		</nav>
		


		<div class="login-container">
			<div class="card mx-auto"
				style="max-width: 500px; min-width: 400px; box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19); border-radius: 15px;">				
				
				<div class="card-header text-center"
					style="font-size: 1.5rem; font-weight: bold; border-radius: 15px;">
					登入</div>
					

				<form class="row g-3" action="/login" method="post">
					<div class="card-body" style="padding: 20px;">
						<div class="mb-3">
							<label for="formGroupExampleInput" class="form-label">電子郵件</label>
							<input type="email" class="form-control"
								id="formGroupExampleInput" name="email" placeholder="">
						</div>
						<div class="mb-3">
							<label for="formGroupExampleInput2" class="form-label">密碼</label>
							<input type="password" class="form-control"
								id="formGroupExampleInput2" name="hashedPassword" placeholder="">
						</div>
						
						<!-- 顯示錯誤訊息 -->
						<c:if test="${not empty errorMessage}">
						    <div class="alert alert-danger" role="alert" style="padding: 5px 15px;">
						        ${errorMessage}
						    </div>
						</c:if>
						
						<!-- 使用 Flexbox 平均分佈 -->
						<div style="display: flex; justify-content: space-between;">
							<button type="button" class="btn btn-outline-forgot">忘記密碼</button>
							<button type="submit" class="btn btn-outline-login">登入</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<footer class="footer">
			<p>© Copyright 2024</p>
			<div class="footer-links">
				<a href="">關於我們</a> <a href="">聯絡我們</a> <a href="">隱私政策</a>
			</div>

		</footer>

	</div>
</body>

</html>