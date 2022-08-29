<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.*, java.text.*, java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="../resources/board/home.css">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">

<title>Board Home</title>
</head>
<body>

	<header class="headerContainer">
		<form action="search" method="post">
			<!--상단 탭 만들기 뼈대구조-->
			<div class="headerContents">
				<!--상단 탭 내용물 감싼구조-->
				<div class="WestagramTag">
					<!--상단 좌측 내용물-->
					<a href="home"><i class="fab fa-instagram"></i> | <span>Wgt</span>agram</a>
				</div>
				<div class="headerSearchBar" style="border-radius: 5px;">
					<!--상단 중앙 내용물-->
					<i class="fas fa-search"></i> <input name="title" type="text"
						placeholder="검색" style="border: none; outline: none;"> <input
						type="submit" value="검색" style="display: none;">
				</div>
			</div>
		</form>
	</header>
	<div class="mypage">
		<a href="mypage"><img id="user"
			src="../resources/board/img/user.png" width="30px" height="30px"
			style="cursor: pointer;"></a>
	</div>

	<div class="body1">
		<div class="feedReactionButton">
			<!-- <button class="heartbtn" onclick="addLike()"><i class="far fa-heart"></i></button> -->
			<div class="boardarea" style="margin-top: 20px;">
				<c:forEach var="item" items="${list}">
						<div class="write" style="margin-bottom: 30px;">
				 	<p style="position:relative; margin-bottom:7px;">[No.${item.number}] ${item.title}</p>
						<img src="../resources/board/img/upload/${item.imgname}.jpg"
							style="width: 370px; height: 330px"><br>
						<div class="emptyheart">
							<a class="likebtn"
								style="display: flex; padding-left: 1px; margin-top: 1px;"></a>
							<div class="feedReaction">
								<span class="liketext">좋아요 <span class="likesresult">0</span>개
								</span>
							</div>

						</div>
						<p>
							<fmt:formatDate pattern="yy년MM월dd일 a hh:mm"
								value="${item.regDate}" />
						</p>
						<h4>${item.title}</h4>
						<br> ${item.content}<br>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<script>
	let like = document.querySelectorAll(".likebtn")
	
	for(let i = 0; i < like.length; i++){
		like[i].addEventListener('click', ()=> {
			like[i].classList.toggle('open')
		})
	}

</script>

</body>
</html>