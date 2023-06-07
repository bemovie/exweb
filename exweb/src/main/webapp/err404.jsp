<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>
	<h1>니가 찾는 페이지는</h1>
	<!-- <img alt="" src="http://localhost:8000/exweb/img/no.jpg" /> -->
	<img alt="" src="/exweb/img/no.jpg" />
	<img alt="" src="${pageContext.request.contextPath}/img/no.jpg" />
	<img alt="" src="<c:out value="${pageContext.request.contextPath}" />/img/no.jpg" />
	
	<!-- <img alt="" src="./img/no.jpg"> 상대경로
	<img alt="" src="img/no.jpg"> 상대경로 -->
	 
</body>
</html>