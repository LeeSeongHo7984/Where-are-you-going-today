<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0" />
<title>position</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/mapcss/map.css'/>" />
</head>
<body>
	<div class="outline" style="font-size: 30px; text-align: center;">
		<div class="inline"
			style="width: 300px; margin: 0 auto; border: 2px solid #DA0037; border-radius: 10px; height: 294px; margin-top: 105px;">
			<form action="position" method="get" style="line-height: 50px;">
				가게이름: ${shop.shopName}<br> 사업자 번호: ${shop.businessNumber}
			</form>

			<form action="position" method="post">
				<label style="maring-top: 20px;">경도(longitude)</label><br> <input
					name="longitude"
					style="border: 1px solid #DA0037; border-radius: 10px; height: 27px; width: 100px;" /><br>
				<label>위도(latitude)</label><br> <input
					style="border: 1px solid #DA0037; border-radius: 10px; height: 27px; width: 100px;"
					name="latitude" /><br>
				<a  target="_blank"
					style="margin-right: 10px; background: none; border: 1px solid #DA0037; border-radius: 10px; font-size: 20px; color: black; text-decoration: none;"
					type="button"
					href="https://www.google.co.kr/maps/search/${shop.shopAddress}">경도,
					위도 찾기</a>
				<input 
				style="background: none; border: 1px solid #DA0037; border-radius: 10px; font-size: 16px;" 
				type="submit" 
				value="등록">
			</form>
		</div>
	</div>
</body>
</html>