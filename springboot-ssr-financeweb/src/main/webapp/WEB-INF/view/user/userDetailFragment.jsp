<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Jakarta JSTL 表單標籤 -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用戶明細</title>
</head>
<body>
    <p><strong>姓名:</strong> ${user.lastName}${user.firstName}</p>
    <p><strong>生日:</strong> 
    	<fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd" />
    </p>
    <p><strong>權限:</strong> ${user.role}</p>
	<c:if test="${not empty userResponse && user.role != 'admin'}">
	    <p><strong>風險類型:</strong> ${userResponse.riskType}</p>
	    <p><strong>總分數:</strong> ${userResponse.totalScore}</p>
	</c:if>

</body>
</html>
