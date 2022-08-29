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
			<h3 style="font-size: 40px;">${barName}</h3>
		</header>
		<hr>
		<div id="content">
			<div style="text-align: center; font-size: 45px;" class="inner">
					현재 대기중인 팀 : ${nowWaitingCount}
			</div>
			<div style="margin-top: 50px;" class="input_wrap">
				<form class="form_style" action="waiting" method="post">
					<h3 style="font-size: 25px;">인원수 입력</h3>
					<input class="input_num" placeholder="인원수" name="num_people"
						type="number" min="1" value="1">
					<div class="input_wrap">
						<input style="margin-left: 20px;" class="input_style" type=button
							value="-" onClick="javascript:this.form.num_people.value--;">
						<input class="input_style" type=button value="+"
							onClick="javascript:this.form.num_people.value++;">
					</div>
			</div>
			<div class="btn_wrap" style="width: 100%;">
				<input onclick="back()" value="뒤로가기" class="prev_btn" style="text-align: center;"> <input style="margin-right: 20px;"
					type="submit" value="웨이팅 하기" class="next_btn">
			</div>
			</form>
		</div>
		<hr>
		<footer id="footer">Copyright (c) 2022 Copyright Holder All
			Rights Reserved.</footer>
	</div>
</body>
</html>
