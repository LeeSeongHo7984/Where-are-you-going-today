<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="../resources/board/mypage.css">
<link rel="stylesheet" type="text/css"
	href="../resources/board/home.css">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">

<title>마이페이지</title>
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
						placeholder=" 게시글 검색" style="border: none; outline: none;">
					<input type="submit" value="검색" style="display: none;">
				</div>
			</div>
		</form>
	</header>



	<div class="mypage">
		<a href="mypage"><img id="user"
			src="../resources/board/img/user.png" width="30px" height="30px"
			style="cursor: pointer;"></a>
	</div>
	<c:forEach var="user" items="${userList}">
 		${user.userId}
 		${user.name}
  		${user.phone}
	</c:forEach>
	<div class="selectlist">
		<form:select path="board" class="selectbox">
			<option value="unknown" style="text-align: center;">&emsp;&emsp;&emsp;&ensp;&nbsp;--
				선택 --</option>
			<c:forEach var="item" items="${board}">
				<option style="text-align: center;"
					value="${item.number}${item.title}${item.imgname}">[No.${item.number}]
					${item.title}</option>
					<img src="../resources/board/img/upload/${item.imgname}.jpg"
							style="width: 30px; height: 30px">
			</c:forEach>
		</form:select>
	</div>
	<div class="updateearea">
		<form action="update" method="post" style="text-align: center;">
			<button class="updatebtn" type="button" value="수정"
				style="position: relative; left: 1%; margin-top: 20px; margin-bottom: 20px; cursor: pointer; z-index: 1000;">수정</button>
		</form>
	</div>
	<div class="deletearea">
		<form action="delete" method="post" style="text-align: center;">
			<button class="deletebtn" type="button" value="삭제"
				style="position: relative; left: 1%; margin-top: 20px; margin-bottom: 20px; cursor: pointer; z-index: 1000;">삭제</button>
		</form>
	</div>
</body>
</html>