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
		function back() {
			history.back();
		}
	</script>
	<div id="wrap">
		<header id="header">
			<h3 style="font-size: 40px;">나의 매장 웨이팅 현황</h3>
		</header>
		<hr>
		<div id="content">
			<div style="text-align: center; font-size: 45px;" class="inner">
				<c:forEach var="waiting" items="${MyShopWaitingList}"
					varStatus="status">
					<c:if test="${waiting.userId != '없음'}">
						<form action="waitingCheck" method="post"
							style="margin-bottom: 55px;">
							순번 : ${status.index+1}<br> 고객ID : <input name="userId"
								value="${waiting.userId}"
								style="opacity: 0; width: 0; height: 0;">${waiting.userId}<br>
							인원수 : ${waiting.num_people}
							<div>
								<input type="submit" value="확인" class="prev_btn"
									style="width: 150px; background: #333333; border-radius: 25px;">
							</div>
						</form>
					</c:if>
					<c:if test="${waiting.userId == '없음'}">
						<div
							style="height: 430px; display: flex; align-items: center; justify-content: center;">
							현재 웨이팅 테이블 없음</div>
					</c:if>
				</c:forEach>
			</div>
			<form action="allWaitingClear" method="post" class="form_style">
				<c:if test="${isUser == '없음'}">
					<div class="btn_wrap">
						<input style="margin-left: -10px;" type="button" onclick="back()"
							value="뒤로가기" class="prev_btn">
					</div>
				</c:if>
				<c:if test="${isUser != '없음'}">
					<div class="btn_wrap">
						<input type="button" onclick="back()" value="뒤로가기"
							class="prev_btn"> <input type="submit" value="내역 초기화"
							class="next_btn" style="margin-right: 10px;">
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
