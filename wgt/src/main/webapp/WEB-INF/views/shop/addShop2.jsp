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
      <header id="header"><h1>2/4</h1><h1>가게 등록</h1></header>
      <hr>
      <div id="content">
        <div class="inner">
          <form action="add_shop3" method="post" id="addForm">
            <p>가게 영업 시간</p>
            <div class="hourBox">
            <select class="select_box" name="shop_hour1">
            	<option value="01:00">01:00</option>
            	<option value="02:00">02:00</option>
            	<option value="03:00">03:00</option>
            	<option value="04:00">04:00</option>
            	<option value="05:00">05:00</option>
            	<option value="06:00">06:00</option>
            	<option value="07:00">07:00</option>
            	<option value="08:00">08:00</option>
            	<option value="09:00">09:00</option>
            	<option value="10:00">10:00</option>
            	<option value="11:00">11:00</option>
            	<option value="12:00">12:00</option>
            	<option value="13:00">13:00</option>
            	<option value="14:00">14:00</option>
            	<option value="15:00">15:00</option>
            	<option value="16:00">16:00</option>
            	<option value="17:00">17:00</option>
            	<option value="18:00">18:00</option>
            	<option value="19:00">19:00</option>
            	<option value="20:00">20:00</option>
            	<option value="21:00">21:00</option>
            	<option value="22:00">22:00</option>
            	<option value="23:00">23:00</option>
            	<option value="24:00">24:00</option>
            </select><span style="font-size:32px;">~</span><select class="select_box" name="shop_hour2">
            	<option value="01:00">01:00</option>
            	<option value="02:00">02:00</option>
            	<option value="03:00">03:00</option>
            	<option value="04:00">04:00</option>
            	<option value="05:00">05:00</option>
            	<option value="06:00">06:00</option>
            	<option value="07:00">07:00</option>
            	<option value="08:00">08:00</option>
            	<option value="09:00">09:00</option>
            	<option value="10:00">10:00</option>
            	<option value="11:00">11:00</option>
            	<option value="12:00">12:00</option>
            	<option value="13:00">13:00</option>
            	<option value="14:00">14:00</option>
            	<option value="15:00">15:00</option>
            	<option value="16:00">16:00</option>
            	<option value="17:00">17:00</option>
            	<option value="18:00">18:00</option>
            	<option value="19:00">19:00</option>
            	<option value="20:00">20:00</option>
            	<option value="21:00">21:00</option>
            	<option value="22:00">22:00</option>
            	<option value="23:00">23:00</option>
            	<option value="24:00">24:00</option>
            </select>
            </div>
            <p>가게 테이블 수</p>
            <select class="select_box" name="shop_table">
            	<option value="1">1</option>
            	<option value="2">2</option>
            	<option value="3">3</option>
            	<option value="3">3</option>
            	<option value="4">4</option>
            	<option value="5">5</option>
            	<option value="6">6</option>
            	<option value="7">7</option>
            	<option value="8">8</option>
            	<option value="9">9</option>
            	<option value="10">10</option>
            	<option value="11">11</option>
            	<option value="12">12</option>
            	<option value="13">13</option>
            	<option value="14">14</option>
            	<option value="15">15</option>
            	<option value="16">16</option>
            	<option value="17">17</option>
            	<option value="18">18</option>
            	<option value="19">19</option>
            	<option value="20">20</option>
            </select>
            <p>테이블당 최대 수용 인원 수</p>
            <select class="select_box" name="shop_max_people">
            	<option value="1">1</option>
            	<option value="2">2</option>
            	<option value="3">3</option>
            	<option value="4">4</option>
            	<option value="5">5</option>
            	<option value="6">6</option>
            	<option value="7">7</option>
            	<option value="8">8</option>
            	<option value="9">9</option>
            	<option value="10">10</option>
            </select>
            <p>가게 전화번호</p>
            <div class="tel_box">
            <select style="width: 100px;" class="select_box" name="shop_tel1">
              <option value="02">02 </option>
              <option value="031">031 </option>
              <option value="032">032 </option>
              <option value="033">033 </option>
              <option value="041">041 </option>
              <option value="042">042 </option>
              <option value="043">043 </option>
              <option value="051">051 </option>
              <option value="052">052 </option>
              <option value="053">053 </option>
              <option value="054">054 </option>
              <option value="055">055 </option>
              <option value="061">061 </option>
              <option value="062">062 </option>
              <option value="063">063 </option>
              <option value="064">064 </option>
            </select>
            <span style="font-size:32px;">-</span>
            <input type="text" name="shop_tel2" class="input_box2" maxlength="4">
            <span style="font-size:32px;">-</span>
            <input type="text" name="shop_tel3" class="input_box2" maxlength="4">
            </div>
            <hr>
            <div class="btn_wrap">
              <input type="button" value="취소하기" onclick="location.href='login'" class="prev_btn">
              <input type="submit" value="다음으로" class="next_btn">
            </div>
          </form>
        </div>
      </div>
      <hr>
      <footer id="footer">Copyright (c) 2022 Copyright Holder All Rights Reserved.</footer>
    </div>
  </body>
</html>
