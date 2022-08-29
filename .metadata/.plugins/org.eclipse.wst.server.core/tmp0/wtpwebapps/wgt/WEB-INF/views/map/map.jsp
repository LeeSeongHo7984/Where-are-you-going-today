<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/mapcss/map.css'/>" />
<style>
</style>
</head>

<body>

	<a class="back" href="<c:url value='/login'/>"><img
		src="../resources/mapcss/img/backicon.png"></a>
	<input id="inputaddr" value="${addr}" />
	<a class="userInformation"> <span></span>
	</a>
	<nav id=gnb>
		<ul>
			<li class="sub1"><span>아이디</span></li>
			<li class="sub2"><span>웨이팅 내역</span></li>
		</ul>
	</nav>
	<form class="header_form" action="map" method="post">
		<br> <select class="selectbox">
			<option>주소</option>
			<option>메뉴</option>
		</select> <input onkeyup="filter()" id="inputSearch" class="inputtext" type="text" name="name"
			required>
		<%int count = 0;%>
		<div id="map" style="width: 370px; height: 700px; margin-left: 10px;"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5b341178fe09d0d9b1f0550b3aa199be&libraries=services"></script>
		<div class="map_wrap">
			<div id="menu_wrap" class="bg_white">
				<hr>
				<ul id="placesList">
		<c:forEach var="shop" items="${find}" varStatus="status">
		<div class="item" style="display:none;">
			<input id="findname${status.index}" value="${shop.name}" onclick="inputText"
				style="width: 0; height: 0; opacity: 0; cursor: default;" />
				<span class="name">${shop.name}</span>
			<input id="longitude${status.index}" value="${shop.longitude}"
				style="width: 0; height: 0; opacity: 0; cursor: default;" />
			<input id="latitude${status.index}" value="${shop.latitude}"
				style="width: 0; height: 0; opacity: 0; cursor: default;" />
		</div>
			<%
			count++;
			%>
		</c:forEach>
				</ul>
				<div id="pagination"></div>
			</div>
		</div>
		
		<input id="count" value="<%=count%>"
			style="width: 0; height: 0; opacity: 0; cursor: default;" />
		
		<button class="searchbtn" value="검색" >검색</button>
		<script>
		const toggleBtn = document.querySelector(".userInformation")
		const gnbBtn = document.querySelector("#gnb")
		const bodytoggle = document.querySelector(".header_form")
		const listBtn = document.querySelector("#menu_wrap")
		const searchbtn = document.querySelector(".searchbtn")
		
		function toggleHandler() {
   			toggleBtn.classList.toggle("open")
   			gnbBtn.classList.toggle("on")
   			bodytoggle.classList.toggle("on")
		}
	
    	toggleBtn.addEventListener("click",toggleHandler);

		function filter() {
			
			var value, name, item, i, background;
			
			value = document.getElementById("inputSearch").value.toUpperCase();
			item = document.getElementsByClassName("item");
			background = document.getElementById("menu_wrap")
			
			for(i = 0; i<item.length; i++){
				name = item[i].getElementsByClassName("name")
				console.log(value.length)
				 if(name[0].innerHTML.toUpperCase().indexOf(value) > -1){
					item[i].style.display = "flex";
					background.style.opacity = "100";
				}
				if(value.length == 0) {
					item[i].style.display = "none";
					background.style.opacity = "0";
				}
			}
		}
		
		var MARKER_WIDTH = 24, // 기본, 클릭 마커의 너비
	    MARKER_HEIGHT = 35, // 기본, 클릭 마커의 높이
	    OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
	    OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
	    OVER_MARKER_WIDTH = 31, // 오버 마커의 너비
	    OVER_MARKER_HEIGHT = 41, // 오버 마커의 높이
	    OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
	    OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
	    CLICK_MARKER_WIDTH = 17,
	    CLICK_MARKER_HEIGHT = 29;
	    
	    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	    // 마커 이미지를 생성합니다    
		var markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
	   		markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
	    	overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH, OVER_MARKER_HEIGHT), // 오버 마커의 크기
	    	overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y), // 오버 마커의 기준 좌표
	    	clickMarkerSize = new kakao.maps.Size(CLICK_MARKER_WIDTH, CLICK_MARKER_HEIGHT);
		
		
	    
	    selectedMarker = null; // 클릭한 마커를 담을 변수
	    selectedContent = null;

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	    mapOption = { 
	        center: new kakao.maps.LatLng(35.865491251524496, 128.5934081998044), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	var positions = []//좌표값을 받을 배열
	var content = [] //가게 이름을 받을 배열
	// 지도 위에 마커를 표시합니다
	for (var i = 0, len = document.getElementById("count").value; i < len; i++) {
	    var gapX = (MARKER_WIDTH), // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
	        originY = (MARKER_HEIGHT) * i, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
	        overOriginY = (OVER_MARKER_HEIGHT) * i, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
	        normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
	        clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
	        overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표
	        
	        positions.push(new kakao.maps.LatLng(document.getElementById("longitude" + i ).value, document.getElementById("latitude" + i ).value)); //좌표값을 받아와 배열에 추가하여 마커를 표시
	        content.push('<div class="wrap"><div class="info"><div class="title">' + 
	        		document.getElementById("findname" + i ).value + 
	        		'<div class="close" onclick="closeOverlay()"></div></div></div></div>');//가게이름을 받아와 배열에 추가
	        // 마커를 생성하고 지도위에 표시합니다
	    addMarker(positions[i],content[i], normalOrigin, overOrigin, clickOrigin);
		
	}
	
	// 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
	function addMarker(position, content, normalOrigin, overOrigin, clickOrigin) {

	    // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
	   	var markerImage = new kakao.maps.MarkerImage(imageSrc, markerSize),
	     	overMarker = new kakao.maps.MarkerImage(imageSrc, overMarkerSize),
	    	clickMarker = new kakao.maps.MarkerImage(imageSrc, clickMarkerSize);
	    
	    // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: position,
	        image: markerImage 
	    });
	    
	    var	overlay = new kakao.maps.CustomOverlay({
	    	content: content,
	    	map: map,
	    	position: position
	    });


	    // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
	    marker.markerImage = markerImage;

	    // 마커에 mouseover 이벤트를 등록합니다
	    kakao.maps.event.addListener(marker, 'mouseover', function() {

	        // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
	        // 마커의 이미지를 오버 이미지로 변경합니다
	        if (!selectedMarker || selectedMarker !== marker) {
	            marker.setImage(overMarker);
	        }
	    });

	    // 마커에 mouseout 이벤트를 등록합니다
	    kakao.maps.event.addListener(marker, 'mouseout', function() {

	        // 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
	        // 마커의 이미지를 기본 이미지로 변경합니다
	        if (!selectedMarker || selectedMarker !== marker) {
	            marker.setImage(markerImage);
	        }
	    });

	    // 마커에 click 이벤트를 등록합니다
	    overlay.setMap(null);
	    kakao.maps.event.addListener(marker, 'click', function() {
	        // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
	        // 마커의 이미지를 클릭 이미지로 변경합니다
	        if (!selectedMarker || selectedMarker !== marker) {

	            // 클릭된 마커 객체가 null이 아니면
	            // 클릭된 마커의 이미지를 기본 이미지로 변경하고
	            !!selectedMarker && selectedMarker.setImage(selectedMarker.markerImage);
	            !!selectedContent && selectedContent.setMap(null);
	            // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
	            marker.setImage(clickMarker);
	            overlay.setMap(map);   
	        }
	        // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
	        selectedMarker = marker;
	        
	        selectedContent = overlay;
	    });
	}
    function closeOverlay() {
        overlay.setMap(null);     
    }
    
 	var geocoder = new kakao.maps.services.Geocoder();
	geocoder.addressSearch(document.getElementById("inputaddr").value, function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    }
	});
	
		</script>
		<div class="bottomShop"></div>


	</form>
</body>
</html>

