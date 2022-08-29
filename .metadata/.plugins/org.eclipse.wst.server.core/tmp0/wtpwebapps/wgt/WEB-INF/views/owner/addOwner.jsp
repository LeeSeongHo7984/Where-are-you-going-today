<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<link rel="stylesheet" href="resources/login/ownerLogin.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>점주 회원가입</title>
</head>

<body>
<a class="back" href="<c:url value='/ownerLogin'/>"><img src="resources/mapcss/img/backicon.png"></a>
	<form action="addOwner" method="post">
		<div style="font-size: 80px; text-align: center; padding-top: 50px;">점주가입</div>
		<div style="text-align: center; font-size: 30px; padding-top: 30px;">아이디 <input type="text" name="ownerId" style="margin-left: 20px; height: 20px;"></div><br>
		<div style="margin-left: 45px; font-size: 30px; padding-top: 30px;">비밀번호 <input type="password" name="passwd" style="margin-left: 20px; height: 20px;"></div><br>
		<div style="margin-left: 86px; font-size: 30px; padding-top: 30px;">이름 <input type="text" name="name" style="margin-left: 20px; height: 20px;"></div><br>
		<div style="margin-left: 10px; font-size: 30px; padding-top: 30px;">사업자 번호 <input type="text" name="bnumber" style="margin-left: 20px; height: 20px;"></div>
		<div style="margin-left: 38px; font-size: 30px; padding-top: 40px;">전화번호 <input type="text" name="phone" style="margin-left: 20px; height: 20px;"></div><br>
		<div style="margin-left: 40px; font-size: 30px; padding-top: 30px;">가게주소 <input type="text" name="addr" style="margin-left: 20px; height: 20px;"></div><br>
		<input type="submit" value="점주가입" class="loginBtn" />
	</form>
</body> 
</html>