<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <link rel="stylesheet" href="resources/initialize.css">
    <link rel="stylesheet" href="resources/shopStyle.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
    <title>가게 등록</title>
  </head>
  <body>
    <div id="wrap">
      <header id="header"><h1>축하합니다</h1></header>
      <hr>
      <div id="content">
        <div class="inner">
          <h1 style="font-size: 52px;">가게 등록 완료!</h1>
          <p style="font-size: 32px;">사장님의 가게가 성공적으로 등록이<br>완료되었어요<br>아래의 버튼을 눌러 지금 바로 등록한<br>가게를 조회해 보세요!</p>
          <button onclick="location.href='shop/viewTempMyShop'" class="next_btn">내 가게 조회</button>
        </div>
      </div>
      <hr>
      <footer id="footer">Copyright (c) 2022 Copyright Holder All Rights Reserved.</footer>
    </div>
  </body>
</html>
