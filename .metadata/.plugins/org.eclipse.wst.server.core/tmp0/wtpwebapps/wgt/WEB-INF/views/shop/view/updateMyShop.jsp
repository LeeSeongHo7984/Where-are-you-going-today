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
      <form method="post" action="updateShop" class="flexForm" enctype="multipart/form-data">
      <span class="text" style="color: #DA0037;">가게 사업자 번호</span>
        <input name="businessNumber" type="text" class="text readonlyinput" readonly value="${shop.businessNumber }"/>
      <span class="text" style="color: #DA0037;">가게 이름</span>
	    <input name="shopName" type="text" class="text readonlyinput" readonly value="${shop.shopName }"/>
      <span class="text" style="color: #DA0037;">가게 번호</span>
      	<input name="shopTel" class="text input_box" value="${shop.shopTel }"/>
      <span class="text" style="color: #DA0037;">가게 주소</span>
      	<input type="button" onclick="sample6_execDaumPostcode()" class="next_btn" value="우편번호 찾기"><br>
		<input type="text" name="shop_address1" id="sample6_postcode" class="text input_box" value="${shop.shopPostCode }">
		<input type="text" name="shop_address2" id="sample6_address" class="text input_box" value="${shop.shopAddress }"><br>
		<input type="text" name="shop_address3" id="sample6_detailAddress" class="text input_box" value="${shop.shopDetailAddress }">
		<input type="text" name="shop_address4" id="sample6_extraAddress" class="text input_box" value="${shop.shopExtraAddress }">
      <span class="text" style="color: #DA0037;">영업 시간</span>
      	<input name="shopHours" class="text input_box" value="${shop.shopHours }"/>
      <span class="text" style="color: #DA0037;">가게 테이블 보유 수</span>
      <input name="shopTables" class="text input_box" value="${shop.shopTables }"/>
      <span class="text" style="color: #DA0037;">가게 테이블당 최대 수용 인원 수</span>
      <input name="shopMaxPeoples" class="text input_box" value="${shop.shopMaxPeoples }"/>
      <span class="text" style="color: #DA0037;">수정 전 가게 사진</span>
      	<img src="../resources/shop/shop_Img/${shop.shopImg }.jpg" style="border-radius: 30px;" />
      	<input name="shopImg" value="${shop.shopImg }" style="display:none;">
            <p style="text-align:center; font-size: 22px; margin-top: 15px;">수정 할 사진을 업로드 해주세요!<br>전의 사진을 그대로 사용하고싶다면<br>사진 업로드를 하지 않으셔도 됩니다.</p>
            <div class="file_upload_wrap">
              <label for="upload_file" class="upload_label">사진 등록</label>
              <input id="upload_file" type="file" name="shop_img" onchange="readURL(this)" accept="image/jpeg, image/png, image/jpg">
              <br>
              <span style="margin-bottom: 15px; font-size: 28px;">미리보기</span>
              <img style="width: 380px; height: 400px;" id="preview" />
            </div>
            <hr>
      	<input type="submit" class="next_btn" style="width: 80%; margin: 30px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 10px black;" value="수정 완료">
      	</form>
      </div>
      <button type="button" name="button" class="prev_btn" style=" width: 80%; margin: 30px 0 25px; border: 5px solid white; border-radius: 30px; box-shadow: 0 10px black;" onclick="location.href='viewMyShop'">뒤로 돌아가기</button>
    </div>
      <script type="text/javascript">
	function readURL(input) {
		const type = input.files[0].name.split('.');
		if(type[1] != "jpg" && type[1] != "png" &&  type[1] != "jpeg"){
			alert("이미지 파일은 (jpg, png, jpeg) 형식만 등록 가능합니다.");
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
	
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
  </script>
  </body>
</html>