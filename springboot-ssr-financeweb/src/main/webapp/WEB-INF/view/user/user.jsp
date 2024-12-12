<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 如果沒有引入 JSTL 標籤庫，JSP 引擎無法理解 JSTL 的標籤 -->
<!-- Jakarta JSTL 表單標籤 -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- JSTL 的核心標籤庫 -->
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!-- JSTL 的格式化標籤庫，主要用來進行數字、日期、貨幣等格式化 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>後台管理</title>
		<link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
		
		
		
		 	 <!-- 引入 Tailwind CSS -->
		    <link rel="stylesheet" href="https://cdn.tailwindcss.com">
		    
		    <!-- 引入 DataTables 的 CSS -->
		    <link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.tailwindcss.css">
		
	</head>
	<body>
			<!-- menu bar include -->
			<%@ include file="/WEB-INF/view/menu.jspf" %>
	
		<div class="container mx-auto p-5">
			<fieldset>
				<legend>User 列表</legend>
				<table id="myTable" class="display " style="width:100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>姓名</th>
							<th>性別</th>
							<th>職業</th>
							<th>電子郵件</th>
							<th>生日</th>
							<th>更新</th>
							<th>刪除</th>
							<th>用戶明細</th>
						</tr>
					</thead>
	
					<tbody>
						<c:forEach var="userDto" items="${userDtos}">
							<tr>
								<td>${userDto.userId}</td>
								<td>${userDto.lastName}${userDto.firstName}</td>
								<td>${userDto.gender}</td>
								<td>${userDto.occupation}</td>
								<td>${userDto.email}</td>
								<td>${userDto.birthDate}</td>
								<td><a class="button-success pure-button"
									href="/admin/user/${userDto.userId}">編輯</a></td>
								<td><a class="button-error pure-button"
									href="/admin/user/delete/${userDto.userId}">刪除</a></td>
								<td><a class="button-darkgray pure-button"
									href="/admin/user/detail/${userDto.userId}">用戶明細</a></td>
							</tr>
						</c:forEach>
					</tbody>
	
	
				</table>
			</fieldset>
		</div>
		<!-- 引入 jQuery -->
	    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	    
	    <!-- 引入 Tailwind JavaScript -->
	    <script src="https://cdn.tailwindcss.com"></script>
	
	    <!-- 引入 DataTables 和 Tailwind 題庫的 JavaScript -->
	    <script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
	    <script src="https://cdn.datatables.net/2.1.8/js/dataTables.tailwindcss.js"></script>
	
	    <!-- 初始化 DataTable -->
	    <script type="text/javascript">
	        $(document).ready(function() {
	            new DataTable('#myTable');
	        });
	    </script>
	
	</body>
</html>