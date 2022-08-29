<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<link rel="stylesheet" href="resources/user/user.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>회원가입</title>
</head>

<body>
	<a class="back" href="<c:url value='/login'/>"><img src="resources/mapcss/img/backicon.png"></a>
	<form action="addUser" method="post" enctype="multipart/form-data">
		<div style="font-size: 60px; text-align: center;">회원가입</div>
		<!-- required는 입력을 안할시 자동으로 입력하라고 알림창을 띄움 -->
		<div style="text-align: center; font-size: 30px; padding-top: 30px;">아이디 
		<input type="text" name="userId" required style="margin-left: 20px; height: 20px;"> </div><br> 
		
		<div style="margin-left: 45px; font-size: 30px; padding-top: 10px;">비밀번호 
		<input type="password" name="passwd" required style="margin-left: 20px; height: 20px;"></div><br>
		
		<div style="margin-left: 86px; font-size: 30px; padding-top: 10px;">이름 
		<input type="text" name="name" required style="margin-left: 20px; height: 20px;"></div><br>
		
		<div style="margin-left: 45px; font-size: 30px; padding-top: 10px;">주민번호 
		<input type="text" name="ssn" required style="margin-left: 20px; height: 20px;"></div><br>
		
		<div style="margin-left: 40px; font-size: 30px; padding-top: 10px;">전화번호 
		<input type="text" name="phone" required style="margin-left: 20px; height: 20px;"></div><br>
		
		<div style="margin-left: 90px; font-size: 30px; padding-top: 10px;">주소 
		<input type="text" name="addr" required style="margin-left: 20px; height: 20px;"></div><br>
		
		<div style="text-align: center; font-size: 30px; padding-top: 10px;">프로필 사진<br> 
		<img id="profileImg" style="width: 100px; height: 100px;"></div>
		<input type="file" multiple="multiple" accept=".jpg, png" name="file" onchange="preview()" accept="image/jpeg, image/png, image/jpg" style="margin-left: 130px;padding-top: 10px;">
		
		<input type="submit" value="회원가입" class="btn" />
	</form>
</body>
	
	<!-- 스크립트 영역 -->
	<script type="text/javascript">
		// input 태그 (name이 file)를 가져옴 
		let fileTag = document.querySelector("input[name=file]");
		
		// 파일태그에 변화가 있을 때 실행될 함수 작성 
		fileTag.onchange = function () {
			
			let imgTag = document.querySelector("#profileImg");
			
			// 파일이 있는지 확인
			if(fileTag.files.length > 0) {
				// 파일을 선택한 경우 미리보기 생성 (이미지 태그 src에 데이터를 넣어주면 됨)
				let reader = new FileReader();
				
				// reader 읽어들이는 작업(onload)를 끝냈을 때 함수 실행, 읽어온 데이터를 함수의 파라미터로 줄 수 있음
				reader.onload = function (data) {
					console.log(data);
					imgTag.src = data.target.result;
				}
				
				reader.readAsDataURL(fileTag.files[0]);
			} else {
				// 취소 버튼 누를 경우
				imgTag.src = "";
			}
		}
	</script> 
</html>