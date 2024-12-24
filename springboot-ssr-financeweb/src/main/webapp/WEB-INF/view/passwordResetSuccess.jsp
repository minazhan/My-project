<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>密碼重設成功</title>

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
	max-width: 450px;
	margin: 100px auto;
	padding: 20px;
	border-radius: 8px;
}

.form-group {
	margin-bottom: 15px;
}

.btn-container {
	margin-top: 10px;
	display: flex;
	justify-content: center;
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
	text-align: center;
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
			<h2>密碼重設成功！</h2>
			<p>您的密碼已成功更新，您現在可以使用新密碼登入。</p>
			<div class="btn-container">
				<a href="/login">返回登入頁面</a>
			</div>
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