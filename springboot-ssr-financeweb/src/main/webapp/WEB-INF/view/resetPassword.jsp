<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>重設密碼</title>

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
	padding-top: 100px;
	/* 確保內容不會被固定的 Navbar 覆蓋 */
	flex: 1;
}

.navbar {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	/* background-color: #cad6e9; */
	/* background-color: #3a3f93; */
	background-color: #3e417d;
	color: #ffffff;
	padding: 5px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	z-index: 1000;
	/* 確保 navbar 在最上層 */
}

.navbar-brand {
	color: #ffe6e6;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	background-color: #f0f2f5;
	/* 淡藍色背景 */
	flex-direction: column;
	padding-bottom: 60px;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

.content {
	width: 100%;
	max-width: 400px;
	margin: 100px auto;
	padding: 20px;
	border-radius: 8px;
	background-color: #f9f9f9;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 15px;
}

.btn-container {
	display: flex;
	justify-content: right;
}

.btn-outline-reset {
	color: #4ab071;
	border: 1px solid #3f9249;
	background-color: transparent;
}

.btn-outline-reset:hover {
	background-color: #e6ffec;
}

.footer {
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	/* 使 Footer 寬度佔滿整個視窗 */
	background-color: #cad6e9;
	color: #000000;
	padding: 15px 15px;
	text-align: center;
	font-size: 0.9em;
	z-index: 1000;
	/* 保證 Footer 在最上層 */
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

p {
	margin: 0px;
}

        .brand-logo{
            width: 150px;
            height: 60px;
            object-fit: contain;
        }
</style>

</head>
<body>

	<div class="container-fluid my-5">
		<nav class="navbar">
			<a href="/" class="navbar-brand">
        		<img src="./img/finance3.PNG" alt="Finance Logo" class="brand-logo">
    		</a>
		</nav>

		<div class="content">
			<h2>重設密碼</h2>
			<form method="post" action="/reset-password">
				<div class="form-group">
					<!-- 可能通過隱藏字段傳遞 token 或 email -->
					<input type="hidden" name="token" value="${token}"> 
					<label for="newPassword">新密碼：</label> 
					<input type="password"
						id="newPassword" name="newPassword" style="width: 100%;" required>
				</div>
				<div class="form-group">
					<label for="confirmPassword">確認新密碼：</label> 
					<input type="password"
						id="confirmPassword" name="confirmPassword" style="width: 100%;" required>
				</div>
				<div class="btn-container">
					<button type="submit" class="btn btn-outline-reset">重設密碼</button>
				</div>
			</form>

			<c:if test="${not empty error}">
				<p style="color: red;">${error}</p>
			</c:if>
			<c:if test="${not empty message}">
				<p style="color: green;">${message}</p>
			</c:if>
		</div>

		<footer class="footer">
			<p>© Copyright 2024</p>
			<div class="footer-links">
				<p>
					聯絡我們<br>(admin@finance.com)
				</p>
			</div>
		</footer>

	</div>


</body>
</html>