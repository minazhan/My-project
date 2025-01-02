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
<title>user_register</title>

<!-- 引入bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
	
<!-- 引入 Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- 引入自訂的 CSS -->
<!-- <link rel="stylesheet" href="style_register.css"> -->

<!--引入 Google Fonts 的字型-->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">

<!--引入 Icons-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<!-- 引入 Bootstrap 和 Datepicker 的相關文件 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

<style>
.container-fluid {
	max-width: 800px;
	margin: 0 auto;
	padding-top: 100px; /* 確保內容不會被固定的 Navbar 覆蓋 */
	flex: 1;
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

.card {
	border-radius: 10px;
	box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24);
}

.content {
	max-width: 800px;
	margin: 0 auto;
	padding-top: 50px; /* 確保內容不會被固定的 Navbar 覆蓋 */
	flex: 1;
}

body {
	font-family: Arial, sans-serif;
	/* background-color: #f5f5f5; */
	background-color: #f0f2f5; /* 淡藍色背景 */
	/* color: #333; */
	/* display: flex; */
	/* flex-direction: column; */
	/* min-height: 100vh; */
	/* padding-bottom: 60px; 確保內容不被 Footer 覆蓋，值可以根據 Footer 的高度調整 */
}
  
.btn{
        background-color: rgba(90, 132, 195, 1);
        float: right;
        border: none;
}

.footer {
	position: relative;
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
	color: #000000;
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
	<!-- <div class="container-fluid" > -->
	<nav class="navbar">
		<a href="/" class="navbar-brand">
        	<img src="./img/finance3.PNG" alt="Finance Logo" class="brand-logo">
    	</a>
	</nav>

	<div class="container my-5">
		<div class="card mx-auto" style="max-width: 600px; min-width: 500px; border-radius: 15px;">
			<div class="card-header text-center"
				style="font-size: 1.5rem; font-weight: bold; border-radius: 15px;">
				註冊</div>
			<div class="card-body" style="padding: 20px;">
				<form:form class="row g-3" action="/register" method="post" modelAttribute="userRegistrationDto">
					<!-- 姓名 -->
					<div class="col-md-6">
						<label for="inputName4" class="form-label">姓名</label> 
						<form:input class="form-control" path="lastName"
							placeholder="範例: 王" aria-label="Last name" />
						<form:errors path="lastName" cssClass="text-danger" />
					</div>
					<div class="col-md-6 ">
						<label for="inputName4" class="form-label">&nbsp;</label> 
						<form:input class="form-control" path="firstName"
							placeholder="範例: 小明" aria-label="First name" />
						<form:errors path="firstName" cssClass="text-danger" />
					</div>

					<!-- 性別 -->
					<div class="col-md-6">
						<label for="inputGender4" class="form-label">性別</label>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input" path="gender"
								id="genderMale" value="男生" checked="checked" /> 
							<label class="form-check-label" for="genderMale"> 男生 </label>
						</div>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input" path="gender"
								id="genderFemale" value="女生" /> 
							<label class="form-check-label" for="genderFemale"> 女生 </label>
						</div>
						<form:errors path="gender" cssClass="text-danger" />
					</div>
					<div class="col-md-6"></div>


					<!-- occupation -->
					<div class="col-md-6">
						<label for="inputOccupation4" class="form-label">職業</label> 
						<form:select
							id="inputOccupation4" class="form-select form-select-sm"
							path="occupation" aria-label="occupation"
							style="padding: 8px 12px;">
							<option selected disabled>請選擇職業</option>
							<option value="1">民意代表主管及經理人員</option>
							<option value="2">專業人員</option>
							<option value="3">技術員及助理專業人員</option>
							<option value="4">事務支援人員</option>
							<option value="5">服務及銷售工作人員</option>
							<option value="6">農林漁牧業生產人員</option>
							<option value="7">技藝有關工作人員</option>
							<option value="8">機械設備操作及組裝人員</option>
							<option value="9">基層技術工及勞力工</option>
							<option value="0">軍人</option>
						</form:select>
						<form:errors path="occupation" cssClass="text-danger" />
					</div>

					<!-- 身分證字號 -->
					<!-- <fieldset class="row" style="margin-top: 20px;">
                                            <div class=" col-form-label col-md-6 pt-0 pb-0">
                                                <label for="inputId4" class="form-label" style="margin-bottom: 0;">身分證字號</label>
                                                <input type="text" class="form-control" name="identityNumber" placeholder="範例 : A123456789" aria-label="Id">
                                            </div>
                                    </fieldset>   -->
					<div class="col-md-6 "></div>

					<!-- 身分證字號 -->
					<div class="col-md-6">
						<label for="inputId4" class="form-label">身分證字號</label> 
						<form:input
							type="text" class="form-control" path="identityNumber"
							placeholder="範例 : A123456789" aria-label="Id" />
						<form:errors path="identityNumber" cssClass="text-danger" />
					</div>
					<div class="col-md-6 "></div>


					<!-- Email -->
					<div class="col-md-6">
						<label for="inputEmail4" class="form-label">Email</label> 
						<form:input
							type="email" class="form-control" path="email"
							placeholder="範例 : abc@gmail.com" id="inputEmail4" />
						<form:errors path="email" cssClass="text-danger" />
					</div>
					<!-- Password -->
					<div class="col-md-6 ">
						<label for="inputPassword4" class="form-label">Password <svg
								xmlns="http://www.w3.org/2000/svg" height="24px"
								viewBox="0 -960 960 960" width="24px" fill="#5f6368">
								<path
									d="M478-240q21 0 35.5-14.5T528-290q0-21-14.5-35.5T478-340q-21 0-35.5 14.5T428-290q0 21 14.5 35.5T478-240Zm-36-154h74q0-33 7.5-52t42.5-52q26-26 41-49.5t15-56.5q0-56-41-86t-97-30q-57 0-92.5 30T342-618l66 26q5-18 22.5-39t53.5-21q32 0 48 17.5t16 38.5q0 20-12 37.5T506-526q-44 39-54 59t-10 73Zm38 314q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z" /></svg></label>
						<form:input type="password" class="form-control" path="hashedPassword"
							id="inputPassword4" />
						<form:errors path="hashedPassword" cssClass="text-danger" />
					</div>



					<!-- Address 
                                    <div class="col-12">
                                        <label for="inputAddress" class="form-label">Address</label>
                                        <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
                                    </div>
                                    <div class="col-12">
                                        <label for="inputAddress2" class="form-label">Address 2</label>
                                        <input type="text" class="form-control" id="inputAddress2"
                                            placeholder="Apartment, studio, or floor">
                                    </div>
                                    -->

					<!-- Birth -->
					<!-- <div class="col-md-4">
                                        <label for="inputYear" class="form-label">生日日期</label>
                                        <input type="text" class="form-control" placeholder="年" id="inputYear">
                                    </div>
                                    <div class="col-md-4">
                                        <label for="inputMonth" class="form-label">&nbsp;</label>
                                        <input type="text" class="form-control" placeholder="月" id="inputMonth">

                                        <select id="inputMonth" class="form-select form-select-sm" aria-label="month" style="padding: 8px 12px;">
                                            <option selected disabled>月</option>
                                            <option value="1">1月</option>
                                            <option value="2">2月</option>
                                            <option value="3">3月</option>
                                            <option value="4">4月</option>
                                            <option value="5">5月</option>
                                            <option value="6">6月</option>
                                            <option value="7">7月</option>
                                            <option value="8">8月</option>
                                            <option value="9">9月</option>
                                            <option value="10">10月</option>
                                            <option value="11">11月</option>
                                            <option value="12">12月</option>
                                        </select>

                                    </div>
                                    <div class="col-md-4">
                                        <label for="inputDay" class="form-label">&nbsp;</label>
                                        <input type="text" class="form-control" placeholder="日" id="inputDay">
                                    </div> -->

					<div class="container mt-3">
						<label for="bootstrap-datepicker">出生日期</label> 
						<form:input type="text"
							class="form-control" id="bootstrap-datepicker" path="birthDate"
							placeholder="選擇日期" />
						<form:errors path="birthDate" cssClass="text-danger" />
					</div>
					
					<!-- role -->
					<div class="col-md-6">
						<label for="inputRole4" class="form-label">權限</label>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input" path="role"
								id="roleUser" value="USER" checked="checked" /> 
							<label class="form-check-label" for="roleUser"> USER </label>
						</div>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input" path="role"
								id="roleAdmin" value="ADMIN" /> 
							<label class="form-check-label" for="roleAdmin"> ADMIN </label>
						</div>
						<form:errors path="role" cssClass="text-danger" />
					</div>
					<div class="col-md-6"></div>

					<script>
						$(document).ready(function() {
							$('#bootstrap-datepicker').datepicker({
								format : "yyyy-mm-dd", // 設置日期格式
								autoclose : true
							// 選擇後自動關閉
							});
						});
					</script>

					<!-- City 
                                    <div class="col-md-6">
                                        <label for="inputCity" class="form-label">City</label>
                                        <input type="text" class="form-control" id="inputCity">
                                    </div>
                                    <div class="col-md-4">
                                        <label for="inputState" class="form-label">State</label>
                                        <select id="inputState" class="form-select">
                                            <option selected>Choose...</option>
                                            <option>...</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <label for="inputZip" class="form-label">Zip</label>
                                        <input type="text" class="form-control" id="inputZip">
                                    </div>
                                    -->
					<div class="col-12">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck">
							<label class="form-check-label" for="gridCheck"> Check me
								out </label>
						</div>
					</div>
					<div class="abc col-12">
						<button type="submit" class="btn btn-primary ">Sign in</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<footer class="footer">
		<p>© Copyright 2024</p>
		<div class="footer-links">
			<p>聯絡我們<br>(admin@finance.com)</p>
			
		</div>

	</footer>

</body>

</html>
