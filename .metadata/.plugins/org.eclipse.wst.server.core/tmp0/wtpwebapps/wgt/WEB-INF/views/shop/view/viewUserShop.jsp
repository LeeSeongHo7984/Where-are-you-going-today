<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/initialize.css">
<link rel="stylesheet" href="../resources/shopStyle.css">
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>가게 정보</title>
</head>
  <body style="background: #DA0037;">
    <div id="wrap">
      <div style="width: 90%; height: 100%; background: white; margin-top: 30px; padding: 20px; box-shadow: 0 15px black; border-radius: 30px;">
        <p class="text" style="font-size: 44px;">${shop.shopName } </p>
      <span class="text" style="color: #DA0037;">가게 사업자 번호</span>
        <p class="text">${shop.businessNumber }</p>
      <span class="text" style="color: #DA0037;">가게 번호</span>
      	<p class="text">${shop.shopTel }</p>
      <span class="text" style="color: #DA0037;">가게 주소</span>
		<p id="sample6_address" class="text" >${shop.shopAddress } ${shop.shopDetailAddress} ${shop.shopExtraAddress}</p>
      	<p id="sample6_postcode" class="text" >${shop.shopPostCode }</p>
      <span class="text" style="color: #DA0037;">영업 시간</span>
      	<p class="text">${shop.shopHours }</p>
      <span class="text" style="color: #DA0037;">가게 테이블 보유 수</span>
      	<p class="text">${shop.shopTables }</p>
      <span class="text" style="color: #DA0037;">가게 테이블당 최대 수용 인원 수</span>
      	<p class="text">${shop.shopMaxPeoples }</p>
      <span class="text" style="color: #DA0037;">가게 사진</span>
      	<img src="../resources/shop/shop_Img/${shop.shopImg }.jpg" style="border-radius: 30px; width: 100%;">
      </div>
      <table style="color: black; border-radius: 30px;">
        <h1 class="title" style="color: white;">가게 메뉴</h1>
      	<c:forEach var="menu" items="${menus }">
            <tr style="background: white;">
              <td style="width: 150px;">
                <img src="../resources/shop/menu_img/${menu.menuImg }.jpg" class="preview img" style="min-width: 150px; height: 150px;" />
                </td>
                <td>
                  <p style= "font-size: 24px;">${menu.menuName }</p>
                  <p style= "font-size: 24px;">${menu.menuIntro }</p>
                  <p style= "font-size: 24px;">${menu.menuPrice }원</p>
                </td>
              </tr>
      	</c:forEach>
      </table>
      <div class="btn_wrap">
        <input type="button" value="뒤로가기" onclick="location.href='../map/map'" class="prev_btn">
        <input type="button" style="background: salmon;" onclick="location.href='../controller/waiting'" value="웨이팅하기" class="next_btn">
      </div>
      <button type="button" name="button" class="next_btn" style=" width: 80%; margin: 15px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 10px black;" onclick="location.href='../board/home'">지금 우리는 🤭</button>
    </div>
  </body>
</html>