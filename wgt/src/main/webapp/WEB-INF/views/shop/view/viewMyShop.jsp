<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/initialize.css">
<link rel="stylesheet" href="../resources/shopStyle.css">
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>가게 메뉴 정보 수정</title>
</head>
  <body style="background: #DA0037;">
    <div id="wrap">
      <div style="width: 90%; height: 100%; background: white; margin-top: 30px; padding: 20px; box-shadow: 0 15px black; border-radius: 30px;">
      <form method="get" action="updateShop" class="flexForm" >
	  <input name="shopName" type="text" class="text readonlyinput input_box2" readonly value="${shop.shopName }"/>
      <span class="text" style="color: #DA0037;">가게 사업자 번호</span>
        <input name="businessNumber"  type="text" class="text readonlyinput input_box2" readonly value="${shop.businessNumber }"/>
      <span class="text" style="color: #DA0037;">가게 번호</span>
      	<input name="shopTel" type="text" class="text readonlyinput input_box2" readonly value="${shop.shopTel }"/>
      <span class="text" style="color: #DA0037;">가게 주소</span>
      	<input type="text" name="shopPostCode" id="sample6_postcode" class="text input_box2" value="${shop.shopPostCode }" readonly style="width:100%;">
		<input type="text" name="shopAddress" id="sample6_address" class="text input_box2" value="${shop.shopAddress }" readonly style="width:100%;">
		<input type="text" name="shopDetailAddress" id="sample6_detailAddress" class="text input_box2" value="${shop.shopDetailAddress }" readonly style="width:100%;">
		<input type="text" name="shopExtraAddress" id="sample6_extraAddress" class="text input_box2" value="${shop.shopExtraAddress }" readonly style="width:100%;">
      <span class="text" style="color: #DA0037;">영업 시간</span>
      	<input name="shopHours" type="text" class="text readonlyinput input_box2" readonly value="${shop.shopHours }"/>
      <span class="text" style="color: #DA0037;">가게 테이블 보유 수</span>
      <input name="shopTables" type="text" class="text readonlyinput input_box2" readonly value="${shop.shopTables }"/>
      <span class="text" style="color: #DA0037;">가게 테이블당 최대 수용 인원 수</span>
      <input name="shopMaxPeoples" type="text" class="text readonlyinput input_box2" readonly value="${shop.shopMaxPeoples }"/>
      <span class="text" style="color: #DA0037;">가게 사진</span>
      	<img src="../resources/shop/shop_Img/${shop.shopImg }.jpg" style="border-radius: 30px; width: 100%;" />
      	<input name="shopImg" type="text" value="${shop.shopImg }" style="display:none;">
      	<input type="submit" class="next_btn" style="margin-top: 10px; width: 80%; margin: 15px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 5px black; margin-top: 10px;" value="가게 정보 수정하기">
      	</form>
      </div>
      <form method="post" action="deleteShopMenu" style="display: flex;
    flex-direction: column;
    align-content: center;
    justify-content: center;
    align-items: center;">
      <table style="color: black; border-radius: 30px;">
        <h1 class="title" style="color: white;">가게 메뉴</h1>
      	<c:forEach var="menu" items="${menus }" varStatus="status">
            <tr style="background: white;">
              <td style="width: 150px;"> 
               <label for=${menu.menuName }><img src="../resources/shop/menu_img/${menu.menuImg }.jpg" class="preview img" style="min-width: 150px; height: 150px;" /></label>
                </td>
                <td>
                <label for=${menu.menuName }>
                  <p style= "font-size: 24px; font-weight: bold;">${menu.menuName }</p>
                  <p style= "font-size: 24px;">${menu.menuIntro }</p>
                  <p style= "font-size: 24px;">${menu.menuPrice }원</p>
                </label>
                </td>             
                <td><input type="checkbox" name="check" value="${menu.menuName }" id="${menu.menuName }" style="width: 30px; height: 30px;"></td>
      			<td><button type="button" onclick="window.location.href='updateMenu?menuName=${menu.menuName}&menuPrice=${menu.menuPrice  }&menuIntro=${menu.menuIntro }&menuImg=${menu.menuImg}'" class="SubmitBtn"  style="height: 107.98px; font-size:25px; font-family: 'KOTRAHOPE';">수정</button>
              </tr>
      	</c:forEach>
      </table>
      <c:if test="${menus[1] != null }">
      <input style="margin-top: 20px; margin-bottom: 20px; background: gray; border-radius: 40px; width: 80%; " type="submit" value="삭제" class="next_btn">      
      </c:if>
      <input type="button" value="메뉴 추가" class="SubmitBtn next_btn" style=" margin-top: 20px; margin-bottom: 20px; color: black; width: 80%; margin: 15px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 5px black; margin-top: 10px; background: white;" onclick="location.href='addMenu'">
      </form>
      <input type="button" value="현재 웨이팅 내역" class="SubmitBtn next_btn" style=" margin-top: 20px; margin-bottom: 20px; color: black; width: 80%; margin: 15px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 5px black; margin-top: 10px; background: white;" onclick="location.href='../controller/waiting_management'">
      <button type="button" name="button" class="next_btn" style=" width: 80%; margin: 15px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 10px black;" onclick="location.href='../board/home'">지금 우리는 🤭</button>
      <button type="button" name="button" class="next_btn" style=" width: 80%; margin: 15px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 10px black;" onclick="location.href='../ownerLogOut'">로그아웃</button>
    </div>
  </body>
</html>