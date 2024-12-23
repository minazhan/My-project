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

<!-- 引入bootstrap，用於使用modal -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- 引入 Tailwind CSS -->
<link rel="stylesheet" href="https://cdn.tailwindcss.com">

<!-- 引入 DataTables 的 CSS -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.tailwindcss.css">

<style>
body {
	background-color: #f0f2f5;
}

.container {
	max-width: 100%; /* 確保容器不會超出瀏覽器視窗寬度 */
	overflow-x: auto; /* 超出容器時允許滾動 */
}

table {
	width: 100%; /* 自適應容器寬度 */
	word-wrap: break-word; /* 讓文字在單元格內換行 */
	table-layout: auto; /* 自動調整每欄的寬度 */
}

/* Modal 背景 */
.modal {
	display: none; /* 初始不顯示 */
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

/* Modal 內容 */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 50%;
	text-align: center;
}

/* 關閉按鈕 */
.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}
</style>


</head>
<body>
	<!-- menu bar include -->
	<%@ include file="/WEB-INF/view/menu.jspf"%>

	<div class="container mx-auto p-5">
		<fieldset>
			<legend style="display: none;">用戶列表</legend>
			<table id="myTable" class="display " style="width: 100%">
				<thead>
					<tr>
						<th>ID</th>
						<th>姓名</th>
						<th>性別</th>
						<th>職業</th>
						<th>電子郵件</th>
						<!--<th>生日</th>-->
						<!-- <th>更新</th>-->
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
							<!--<td>${userDto.birthDate}</td>-->
							<!-- <td><a class="button-success pure-button"
									href="/admin/user/${userDto.userId}">編輯</a></td> -->
							<td><a class="button-error pure-button"
								href="/admin/user/delete/${userDto.userId}">刪除</a></td>
							<td>
								  <a class="button-darkgray pure-button"
								href="/admin/user/detail/${userDto.userId}">用戶明細</a>
							</td>

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
	<script
		src="https://cdn.datatables.net/2.1.8/js/dataTables.tailwindcss.js"></script>

	<!-- 用於使用modal -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>



	<script type="text/javascript">
	
		<!-- 初始化 DataTable -->
		$(document).ready(function() {
			$('#myTable').DataTable({
				lengthChange : false, // 移除 "entries per page"
				pageLength : 10, // 設定每頁顯示筆數
				language : {
					"sInfo" : "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
					"sSearch" : "搜尋:"
				}

			});
		});
		
		

		
	</script>



</body>
</html>