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
<title>로그인</title>
</head>

<body>
	<button type="button" onclick="location.href='ownerLogin';" class="owner">점주 로그인</button>
	<form action="login" method="post">
		<div class="login">로그인</div>
		<div style="text-align: center; margin-bottom: 20px; font-size: 50px;">아이디</div>
		<div style="text-align: center;"><input type="text" name="userId" style="width: 150px; height: 20px; text-align: center;" required></div>
		<div style="text-align: center; margin-top: 30px; margin-bottom: 20px; font-size: 50px;">비밀번호</div><div style="text-align: center;">
		<input type="password" name="passwd" style="width: 150px; height: 20px; text-align: center;" required></div><br>
		<input type="submit" value="로그인" class="loginBtn"><br>
	</form>
	<button type="button" class="addBtn" onclick="location.href='addUser';">회원가입</button>
</body>
</html>