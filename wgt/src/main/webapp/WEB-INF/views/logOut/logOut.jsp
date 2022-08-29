<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<link rel="stylesheet" href="resources/login/login.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>로그아웃</title>
</head>

<body>
	<div style="font-size: 80px; text-align: center; padding-top: 150px; padding-bottom: 120px;">로그아웃<br> 하시겠습니까</div>
	<form action="logOut" method="post">
		<input type="submit" class="addBtn" value="예" />
	</form>
		<button type="button" class="addBtn" onclick="location.href='map/map';">아니요</button>
</body>
</html>