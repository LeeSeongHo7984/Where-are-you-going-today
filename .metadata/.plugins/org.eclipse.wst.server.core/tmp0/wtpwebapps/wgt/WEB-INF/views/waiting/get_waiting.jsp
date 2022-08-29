<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<link rel="stylesheet" href="../resources/initialize.css">
<link rel="stylesheet" href="../resources/shopStyle.css">
<link rel="stylesheet" href="../resources/waitingStyle.css">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>Add Waiting</title>
</head>
<body>
	<script type="text/javascript">
		function go_home() {
			window.location.href = "/mapController";
			locate.reload();
		}
	</script>
	<div id="wrap">
		<header id="header">
			<h3 style="font-size: 40px;">나의 웨이팅 내역</h3>
		</header>
		<hr>
		<div id="content">
			<div style="text-align: center; font-size: 45px;" class="inner">
				<c:forEach var="x" items="${waiting}" varStatus="status">
							매장명 : <br>${x.barName}<br>
							매장 전화번호 : <br>${shopTel}<br>
							인원 : ${x.num_people}명<br>
							총 대기팀 : ${allCount}팀 <br>
							내 앞 대기팀 : ${frontCount}팀<br>
					<span style="color: red;">${msg}</span>
				</c:forEach>

			</div>
			<form action="get_waiting" method="post" class="form_style">
				<c:if test="${shopTel != '-'}">
					<div class="btn_wrap">
						<a class="prev_btn" style="width: 386px; height: 63px; line-height: 30px;"
							aria-current="page" href='<c:url value="/map/map"/>'>홈</a> <input
							type="submit" value="웨이팅 취소" class="next_btn">
					</div>
				</c:if>
				<c:if test="${shopTel == '-'}">
					<div class="btn_wrap">
						<a class="prev_btn" style="margin-right: 10px;" aria-current="page"
							href='<c:url value="/map/map"/>'>홈</a>
					</div>
				</c:if>
			</form>
		</div>
		<hr>

		<footer id="footer">Copyright (c) 2022 Copyright Holder All
			Rights Reserved.</footer>
	</div>
</body>
</html>
