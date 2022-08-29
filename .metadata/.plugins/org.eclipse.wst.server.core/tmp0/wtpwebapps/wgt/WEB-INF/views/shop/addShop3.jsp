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
      <header id="header"><h1>3/4</h1><h1>가게 사진 등록</h1></header>
      <hr>
      <div id="content">
        <div class="inner">
          <form action="add_shop4" method="post" id="addForm" enctype="multipart/form-data">
            <p>사장님만의 자신있는<br>가게 사진을 등록해주세요!</p>
            <div class="file_upload_wrap">
              <label for="upload_file" class="upload_label">사진 등록</label>
              <input id="upload_file" type="file" name="shop_img" onchange="readURL(this)" accept=".jpg">
              <br>
              <span style="margin-bottom: 15px; font-size: 28px;">미리보기</span>
              <img style="width: 380px; height: 400px;" id="preview" />
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
  <script type="text/javascript">
	function readURL(input) {
		const type = input.files[0].name.split('.');
		if(type[1] != "jpg"){
			alert("이미지 파일은 jpg 형식만 등록 가능합니다.");
			document.getElementById('preview').src = "";
			document.getElementById('img').value = null;
			return false;
		}
		  if (input.files && input.files[0]) {
		    var reader = new FileReader();
		    reader.onload = function(e) {
		      document.getElementById('preview').src = e.target.result;
		    };
		    reader.readAsDataURL(input.files[0]);
		  } else {
		    document.getElementById('preview').src = "";
		  }
		}
  </script>
</html>
