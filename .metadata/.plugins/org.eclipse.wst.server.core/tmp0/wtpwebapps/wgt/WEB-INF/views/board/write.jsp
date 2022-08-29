<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>게시판 - 글쓰기</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="../resources/board/write.css">
<link rel="stylesheet" type="text/css"
	href="../resources/board/home.css">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
</head>
<body>
	<header class="headerContainer">
		<!--상단 탭 만들기 뼈대구조-->
		<form action="home" method="post">
			<div class="headerContents">
				<!--상단 탭 내용물 감싼구조-->
				<div class="WestagramTag">
					<!--상단 좌측 내용물-->
					<a href="home"><i class="fab fa-instagram"></i> | <span>Wgt</span>agram</a>
				</div>
				<div class="headerSearchBar" style="border-radius: 5px;">
					<!--상단 중앙 내용물-->
					<i class="fas fa-search"></i> <input name="title" type="text"
						placeholder=" 게시글 검색" style="border: none; outline: none;"> <input
						type="submit" value="검색" style="display: none;">
				</div>
			</div>
		</form>
	</header>
	
		<div class="writearea">
			<form action="write" method="post" style="text-align:center;">
				<button class="writebtn" type="button" value="글쓰기" onclick = "location.href='write'"
					style="position: relative; left: 1%; margin-top:20px; margin-bottom: 20px; cursor: pointer; z-index: 1000;">글쓰기</button>
			</form>
		</div>
	<div class="mypage">
		<a href="mypage"><img id="user"
			src="../resources/board/img/user.png" width="30px" height="30px"
			style="cursor: pointer;"></a>
	</div>
	<h3 style="text-align: center; margin-top: 35px;">게시글 작성</h3>
	<div class="write_area">
		<form action="write" method="post" enctype="multipart/form-data"
			style="text-align: center; display: grid; justify-content: center; margin-top: 40px;">
			<span>제목</span>
			<textarea class="title_area" name="title" required="required"
				maxlength="50" placeholder="제목을 입력하세요(최대 50자)"></textarea>
			<span>내용</span>
			<textarea class="content_area" name="content" required="required"
				maxlength="150" placeholder="내용을 입력하세요(최대 150자)"></textarea>
			사진 선택 : <span><input type="file" accept=".jpg" name="file"
				required="required"><br></span> <input type="submit"
				class="add" value="등록" required="required"><br>
		</form>
		<button class="go_home" onclick="location.href='home'">목록으로</button>
	</div>

</body>
</html>