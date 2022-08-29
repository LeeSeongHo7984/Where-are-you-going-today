<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.List, java.net.URLEncoder"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/incl/button.css'/>" />
</head>

<body>
	<a class="userInformation"> <span></span></a>
		<nav id=gnb>
		<ul>
			<!-- userId를 세션으로 받아서 사용하세요  -->
			<li class="sub1"><span>${userId}님<br> 반가워요 !</span></li>
			<hr style="border: none; background-color: #DA0037; margin-bottom: 20px; height: 2px; width: 200px;">
			<li class="sub2">
				<form id="submitID" action="go_get_waiting" method="post">
					<a onclick="submit_form()">나의 웨이팅</a>
				</form>
			</li>
			<li class="sub3"><a onclick="location.href='/wgt/userInfo';">회원정보
					보기</a></li>
			<li class="sub4"><a onclick="location.href='/wgt/logOut';">로그아웃</a>
			</li>
		</ul>
	</nav>
</body>
<script>
<%-- 
자기 스크립트로 복사 붙여넣기 해서 사용해주세요 여기서 작동하게 할려고 했는데 안되네요

-1 아래 코드를 햄버거 버튼 넣고 싶은곳에 넣어주세요 잘 모르겠으면 예시 봐주세요
<jsp:include page="../incl/button.jsp">
<jsp:param name="subtitle" value="<%=URLEncoder.encode(\"map: map.jsp\", \"UTF-8\")%>" />
</jsp:include>

예시 
<jsp:include page="../incl/button.jsp">
																	↓ 자신의 jsp 이름
<jsp:param name="subtitle" value="<%=URLEncoder.encode(\"map: map.jsp\", \"UTF-8\")%>" />
															   ↑ 자신의 패키지 이름
</jsp:include>
 --%>
/*
 0.
${userId}를 사용 할 수 있게 세션 먼저 받아주세요

 1.
이걸로 감싸면 뒤에 화면 opacity가 15%로 변합니다
<div class="header_form"></div>


 2.
여기를 클릭하면 상세보기 삭제 (감싸서 쓰세요) 만들어도 되고
<div class="click"></div>



 3.
아이디값에 해당하는 부분을 클릭하면 상세보기 삭제(스크립트) 아이디 적어서 쓰시면 됩니다. 
변수 이름 바꿔서 사용

예시 const [원하는 변수명]click = document.getElementById('[아이디값]')


 4. 실제 사용코드 
 위에 1번을 사용했으면 사용해야 하는 코드
const bodytoggle = document.querySelector(".header_form")

위에 2번을 사용했으면 사용해야 하는 코드
const bodyClick = document.querySelector(".click")

햄버거 버튼 클릭 이벤트 실행에 필요한 필수 코드
const toggleBtn = document.querySelector(".userInformation")
const gnbBtn = document.querySelector("#gnb")


5. 동작 함수 선언

		토글을 사용하여 오른쪽에서 화면 출현
		
		function toggleHandler() {
			toggleBtn.classList.toggle("open")
			gnbBtn.classList.toggle("on")
			bodytoggle.classList.toggle("on")
		}
		
		
		토글이 아닌 사용자가 지정한 곳을 클릭했을때 토글들의 클래스를 삭제하는 함수
		 function removeOn() {
			bodytoggle.classList.remove("on")
			toggleBtn.classList.remove("open")
			gnbBtn.classList.remove("on")

		
		
		
		
		위에 3번에 예시를 사용했다면
예시 const [원하는 변수명] = document.getElementById('[아이디값]')
		      ↓
		  [위에 선언한 변수].addEventListener("click", [function 이름 사용 (아마 removeOn ?)])
		(ex)toggleBtn.addEventListener("click", removeOn);
		이렇게 사용하시면 됩니다.
		
		토글 이벤트를 위한 addEventListener
		toggleBtn.addEventListener("click", toggleHandler);
		
		위에 2번에 해당하는 부분을 클릭했을때 원래 화면으로 돌아오는 addEventListener
		bodyClick.addEventListener("click", removeOn);
		*/		
</script>
</html>