<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.*, java.text.*, java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page isELIgnored="false"%>
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
<link rel="stylesheet" type="text/css"
	href="../resources/board/mypage.css">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">

<title>Board Home</title>
</head>
<body>
	<script>
	function reload(){
		location.reload();
	}
</script>

	<header class="headerContainer">
		<!--상단 탭 만들기 뼈대구조-->
		<form action="home" method="post">
			<div class="headerContents">
				<!--상단 탭 내용물 감싼구조-->
				<div class="WestagramTag">
					<!--상단 좌측 내용물-->
					<a href="home"><i class="fab fa-instagram"></i> | <span>${shop}</span></a>
				</div>
				<div class="headerSearchBar" style="border-radius: 5px;">
					<!--상단 중앙 내용물-->
					<i class="fas fa-search"></i> <input name="title" type="text"
						placeholder=" 게시글 검색" style="border: none; outline: none;">
					<input type="submit" value="검색" style="display: none;">
				</div>
				<%-- <p>[${shop}]</p> --%>
			</div>
		</form>
	</header>

	<div class="writearea">
		<form action="write" method="post" style="text-align: center;">
			<c:if test="${ownerchk != true}">
				<button class="writebtn" type="button" value="글쓰기"
					onclick="location.href='write'"
					style="position: relative; left: 0.5%; margin-top: 20px; cursor: pointer; z-index: 1000;">글쓰기</button>
			</c:if>
			<c:if test="${ownerchk == true}">
				<button class="writebtn" style="display: none;"></button>
			</c:if>
		</form>
	</div>
	<div class="mypage">
		<c:if test="${ownerchk != true}">
			<a href="mypage"><img id="user"
				src="../resources/board/img/user.png" width="30px" height="30px"
				style="cursor: pointer;"> </a>
		</c:if>
		<c:if test="${ownerchk == true}">
			<a href="mypage"><img id="user"
				src="../resources/board/img/user.png" width="30px" height="30px"
				style="cursor: pointer; display: none;"> </a>
		</c:if>
		<c:if test="${ownerchk != true}">
			<a href="../map/map;"><img id="gohome"
				src="../resources/board/img/back.png" width="30px" height="30px"
				style="cursor: pointer;"> </a>
		</c:if>
		<c:if test="${ownerchk == true}">
			<a href="../shop/viewMyShop;"><img id="gohome"
				src="../resources/board/img/back.png" width="30px" height="30px"
				style="cursor: pointer;"> </a>
		</c:if>
	</div>

	<div class="body1">
		<div class="feedReactionButton">
			<div class="boardarea" style="margin-top: 20px;">
				<c:forEach var="item" items="${board}">
					<div class="write" style="margin-bottom: 30px;">
						<img src="../resources/board/img/upload/${item.imgname}.jpg"
							style="width: 370px; height: 330px; border-radius: 5px;"><br>
						<form action="likes" method="get">
							<input style="display: none;" name="number"
								value="${item.number}" type="text">
							<div class="likearea">
								<c:if test="${ownerchk != true}">
									<button>
										<img id="likeCSS"
											src="../resources/board/img/${item.likeImg}.png" width="30px"
											height="30px" style="cursor: pointer;">
									</button>
								</c:if>
								<c:if test="${ownerchk == true}">
									<button type="button">
										<img id="likeCSS" src="../resources/board/img/likeheart.png"
											width="30px" height="30px" style="cursor: pointer;">
									</button>
								</c:if>
								<div class="feedReaction">
									<span class="liketext">좋아요 <span class="likesresult"><input
											type="text" id="result" value="${item.likecount}"
											name="likecount"
											style="border: none; width: 7px; background: none;"
											onfocus="this.blur()"></span>개
									</span>
								</div>
							</div>
						</form>
						<p class="homeregdate">
							<fmt:formatDate pattern="yy년MM월dd일 a hh:mm"
								value="${item.regDate}" />
						</p>
						<h4>${item.title}</h4>
						<br> ${item.content}<br>
					</div>
					<c:if test="${ownerchk == true}">
						<div class="update_delete_area"
							style="display: flex; justify-content: space-evenly; height: 30px; border-bottom: 2px solid #f1f1f1;">
							<div class="deletearea">
								<button class="deletebtn" type="button" value="삭제"
									onclick="delCheck(${item.number})"
									style="position: relative; left: 1%; margin: 0 auto; cursor: pointer;">삭제</button>
							</div>
						</div>
					</c:if>
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

	function delCheck(num) {
		const link = "delete?number=" + num;
		if(confirm("정말 삭제하시겠습니까?")){
			/*console.log(num);*/
 			alert("삭제를 완료하였습니다.");
			location.href=link;
		}else{
			alert("삭제를 취소하였습니다.")
		}
	};
</script>

</body>
</html>