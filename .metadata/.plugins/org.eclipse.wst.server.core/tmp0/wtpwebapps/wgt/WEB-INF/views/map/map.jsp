<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0" />
<title>WGT MAP</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/mapcss/map.css'/>" />
<style>
</style>
</head>

<body id="body">
	<script>
		function submit_form() {
			document.getElementById('submitID').submit();

		}
	</script>
	<!--검색어 입력에 맞는 주소로 이동.-->
	<input id="inputaddr" value="${addr}" style="display: none;" />
	<nav id=gnb>
		<ul>
			<li class="sub1"><span>${userId}님<br> 반가워요 !
			</span></li>
			<hr
				style="border: none; background-color: #DA0037; margin-bottom: 20px; height: 2px; width: 200px;">
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
	<div class="header_form">
		<form action="map" method="post">
			<div class=headerMenu>
				<ul>
					<li><a class="back" href="<c:url value='/login'/>"><img
							src="../resources/mapcss/img/backicon.png"></a></li>
					<!--검색어 입력창-->
					<li><input onkeyup="filter()" id="inputSearch"
						class="inputtext" type="text" value="" required></li>
					<li><jsp:include page="../incl/button.jsp">
        				 <jsp:param name="subtitle"
            			value="<%=URLEncoder.encode(\"map: map.jsp\", \"UTF-8\")%>" />
      					</jsp:include>
      				</li>
				</ul>
			</div>
			<div class="click"
				style="top: 75px; height: 96px; width: 100%; position: relative;">
			</div>
			<%
			// 스크립트 반복문 사용을 위한 count 선언
			int count = 0;
			%>
			<div id="map" onclick=" filterEvent()"
				style=" top:98px; width: 370px; height: 650px; margin-left: 10px;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5b341178fe09d0d9b1f0550b3aa199be&libraries=services"></script>
			<div class="map_wrap">
				<div id="menu_wrap" class="bg_white">
					<ul id="placesList">
						<!--모든 가게이름을 다 불러와 맵에 마크와 컨테츠 표현-->
						<c:forEach var="shop" items="${shopFind}" varStatus="status">
							<div class="item"
								style="display: none; border-bottom: solid 1px #DA0037;"
								onclick="location.href='../shop/viewUserShop?shopName=${shop.shopName}'">
								<input name="shopName" class="shop" id="findname${status.index}"
									value="${shop.shopName}" disabled
									style="text-align: center; width: 100%; height: 40px; border: 0; background: none; font-size: 38px; font-weight: bold; color: #DA0037; font-family: 'KOTRAHOPE';" />
								<input id="shopAddress${status.index}"
									value="${shop.shopAddress}" style="display: none;">
								<p class="name" style="display: none">${shop.shopName}</p>
								<p class="address"
									style="line-height: 30px; text-align: center; font-size: 15px;">${shop.shopAddress}</p>
								<p style="text-align: center; font-size: 24px;">${shop.shopTel}</p>
								<span class="menuTitle" style="z-index: 999;">메뉴</span>
							</div>

							<%
							// 스크립트 반복문 사용을 위한 카운트 증가
							count++;
							%>
							<c:set var="menu" value="${menuList[status.index]}" />
							<div class="line" style="display: none;">
								<c:forEach var="x" items="${menu}" varStatus="t">
									<div class="menulist${status.index}"
										id="findmenu${status.index}${t.index}" style="display: none;">
										<span class="menu" style="line-height: 24px;">${menuList[status.index][t.index].menuName}&nbsp;
											- &nbsp; </span><span class="menu">${menuList[status.index][t.index].menuPrice}원</span>
									</div>
								</c:forEach>
							</div>
						</c:forEach>

						<!--위도와 경도를 불러와 등록되어 있는 가게 위치 표시-->
						<c:forEach var="shop" items="${find}" varStatus="status">
							<input id="longitude${status.index}" value="${shop.longitude}"
								style="display: none;" />
							<input id="latitude${status.index}" value="${shop.latitude}"
								style="display: none;" />
						</c:forEach>
					</ul>
				</div>
			</div>
			<!--id값을 이용하여 스크립트에 반복문 사용을 위한 카운트 등록-->
			<input id="count" value="<%=count%>" style="display: none;" />
		</form>
	</div>
	<script>
		const bodyClick = document.querySelector(".click")
		const bodytoggle = document.querySelector(".header_form")
		const gnbBtn = document.querySelector("#gnb")
		const toggleBtn = document.querySelector(".userInformation")
		
		const count = document.getElementById("count").value
		const searchbtn = document.querySelector(".searchbtn")
		const shopName = document.querySelector(".shop")
		const filteritemClose = document.querySelector(".item")
		const filterClose = document.querySelector("#menu_wrap")
		const line = document.querySelector(".line")
		var mapClick = document.getElementById('map')

		function onClicksubMit() {
			bodytoggle.submit(event.target.value);
		}

		function toggleHandler() {
			toggleBtn.classList.toggle("open")
			gnbBtn.classList.toggle("on")
			bodytoggle.classList.toggle("on")
		}

		function removeOn() {
			bodytoggle.classList.remove("on")
			toggleBtn.classList.remove("open")
			gnbBtn.classList.remove("on")
		}

		function filterEvent() {
			filterClose.style.opacity = "0";
			filterClose.style.left = "-270px";
			filteritemClose.style.display = "none";
			line.style.display = "none";
		}

		mapClick.addEventListener("click", filterEvent);

		toggleBtn.addEventListener("click", toggleHandler);
		
		mapClick.addEventListener("click", removeOn);
		bodyClick.addEventListener("click", removeOn);

		function filter() {

			var value, name, item, i, background, menuList, menu, menuLine;

			value = document.getElementById("inputSearch").value.toUpperCase();
			item = document.getElementsByClassName("item");
			background = document.getElementById("menu_wrap")
			menuLine = document.getElementsByClassName("line")
			menuList = []

			for (i = 0; i < item.length; i++) {
				menuList.push(document.getElementsByClassName("menulist" + i));
				name = item[i].getElementsByClassName("name")

				for (j = 0; j < menuList[i].length; j++) {
					menu = menuList[i][j].getElementsByClassName("menu")

					if (name[0].innerHTML.toUpperCase().indexOf(value) > -1) {
						item[i].style.display = "block";
						//menuTitle.style.display ="block";
						menuList[i][j].style.display = "block";
						menuLine[i].style.display = "block";
						background.style.opacity = "100";
						background.style.left = "0";
					} else {
						item[i].style.display = "none";
						menuList[i][j].style.display = "none";
						menuLine[i].style.display = "none";
					}

					if (value.length == 0) {
						menuList[i][j].style.display = "none";
						//menuTitle.style.display ="none";
						item[i].style.display = "none";
						background.style.opacity = "0";
						background.style.left = "-270px";
						menuLine[i].style.display = "none";
					}
				}
			}
		}

		var MARKER_WIDTH = 24, // 기본, 클릭 마커의 너비
		MARKER_HEIGHT = 35, // 기본, 클릭 마커의 높이
		GPS_MARKER_WIDTH = 20, GPS_MARKER_HEIGHT = 20, OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
		OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
		OVER_MARKER_WIDTH = 31, // 오버 마커의 너비
		OVER_MARKER_HEIGHT = 41, // 오버 마커의 높이
		OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
		OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
		CLICK_MARKER_WIDTH = 17, CLICK_MARKER_HEIGHT = 29;

		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		// 마커 이미지를 생성합니다    
		var markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
		markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
		overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH,
				OVER_MARKER_HEIGHT), // 오버 마커의 크기
		overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y), // 오버 마커의 기준 좌표
		clickMarkerSize = new kakao.maps.Size(CLICK_MARKER_WIDTH,
				CLICK_MARKER_HEIGHT), gpsMarkerSize = new kakao.maps.Size(
				GPS_MARKER_WIDTH, GPS_MARKER_HEIGHT);

		selectedMarker = null; // 클릭한 마커를 담을 변수
		selectedContent = null;

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapOption = {
			center : new kakao.maps.LatLng(35.8704973, 128.5953778), // 지도의 중심좌표
			level : 4
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		if (navigator.geolocation) {

			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {

				var lat = position.coords.latitude, // 위도
				lon = position.coords.longitude; // 경도

				var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다

				// 마커와 인포윈도우를 표시합니다
				displayMarker(locPosition);

			});

		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

			var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), message = 'geolocation을 사용할수 없어요..'

			displayMarker(locPosition);
		}
		var gpsImgSrc = "../resources/mapcss/img/gps.png"
		var gpsImg = new kakao.maps.MarkerImage(gpsImgSrc, gpsMarkerSize)
		// 지도에 마커와 인포윈도우를 표시하는 함수입니다
		function displayMarker(locPosition) {

			// 마커를 생성합니다
			var gpsMarker = new kakao.maps.Marker({
				map : map,
				position : locPosition,
				image : gpsImg
			});
			// 지도 중심좌표를 접속위치로 변경합니다
			map.setCenter(locPosition);
		}

		var positions = []//좌표값을 받을 배열
		var content = [] //가게 이름을 받을 배열
		var inputText = []
		var menuSelect = []
		// 지도 위에 마커를 표시합니다
		for (var i = 0, len = count; i < len; i++) {
			var gapX = (MARKER_WIDTH), // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
			originY = (MARKER_HEIGHT) * i, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
			overOriginY = (OVER_MARKER_HEIGHT) * i, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
			normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
			clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
			overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표

			positions.push(new kakao.maps.LatLng(document
					.getElementById("longitude" + i).value, document
					.getElementById("latitude" + i).value)); //좌표값을 받아와 배열에 추가하여 마커를 표시

			content
					.push('<div class="wrap"><div class="info"><div class="title">'
							+ document.getElementById("findname" + i).value
							+ '</div></div></div>');//가게이름을 받아와 배열에 추가

			inputText.push(document.getElementById("findname" + i).value)

			// 마커를 생성하고 지도위에 표시합니다
			addMarker(positions[i], inputText[i], content[i], normalOrigin,
					overOrigin, clickOrigin);
		}
		// 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
		function addMarker(position, inputText, content, normalOrigin,
				overOrigin, clickOrigin) {

			var markerImage = new kakao.maps.MarkerImage(imageSrc, markerSize), overMarker = new kakao.maps.MarkerImage(
					imageSrc, overMarkerSize), clickMarker = new kakao.maps.MarkerImage(
					imageSrc, clickMarkerSize);

			// 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
			var marker = new kakao.maps.Marker({
				map : map,
				position : position,
				image : markerImage
			});

			var overlay = new kakao.maps.CustomOverlay({
				content : content,
				map : map,
				position : position
			});

			marker.markerImage = markerImage;
			// 마커에 click 이벤트를 등록합니다
			overlay.setMap(null);
			kakao.maps.event.addListener(marker, 'click', function() {
				// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
				// 마커의 이미지를 클릭 이미지로 변경합니다
				for (var i = 0, len = count; i < len; i++) {
					document.getElementById("inputSearch").value = inputText
				}

				if (!selectedMarker || selectedMarker !== marker) {
					// 클릭된 마커 객체가 null이 아니면
					// 클릭된 마커의 이미지를 기본 이미지로 변경하고
					!!selectedMarker
							&& selectedMarker
									.setImage(selectedMarker.markerImage);
					!!selectedContent && selectedContent.setMap(null);
				}
				filter()

				// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경, 컨테츠를 띄워줌
				if (marker.markerImage != clickMarker) {
					marker.setImage(clickMarker)
					overlay.setMap(map)

				}

				// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
				selectedMarker = marker;
				selectedContent = overlay;

			});

			kakao.maps.event.addListener(map, 'click', function() {
				if (!overlay.setMap(null)) {
					overlay.setMap(null);
					marker.setImage(markerImage)
				}
			})
		}
		var geocoder = new kakao.maps.services.Geocoder();
		geocoder.addressSearch(document.getElementById("inputaddr").value,
				function(result, status) {

					// 정상적으로 검색이 완료됐으면 
					if (status === kakao.maps.services.Status.OK) {

						var coords = new kakao.maps.LatLng(result[0].y,
								result[0].x);

						// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
						map.setCenter(coords);
					}
				});
	</script>

</body>
</html>
