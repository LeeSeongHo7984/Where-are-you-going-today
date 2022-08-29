# (WGT) Where-are-you-going-today
+ 지도 매장위치 표시 및 매장 웨이팅 등록, 소규모 SNS 서비스 사이트
+ 2022.08.08 ~ 2022.08.22
+ 모바일 인터페이스

## 팀 구성원
+ 5명 ( [본인](https://github.com/LeeSeongHo7984) [상규](https://github.com/parkSangGyu98) [태영](https://github.com/wed456) [기열](https://github.com/BaekKiYeol) [태우](https://github.com/workhan0918) )

## 사용한 기술 및 환경
+ eclipse
+ Spring
+ MySQL
+ JSP
+ HTML/CSS
+ Java
+ JavaScript
+ bootstrap
+ tomcat 8.5
+ git

## 구현기능
+ 사용자
  + 회원가입, 회원정보 수정, 회원탈퇴, 로그인, 로그아웃
  + 매장정보 조회 및 검색
  + 매장위치 확인 (카카오지도 API)
  + 웨이팅 등록 및 조회, 취소
  + SNS 게시글 등록, 조회, 수정, 삭제, 좋아요 기능
+ 매장 점주
  + 회원가입, 로그인, 로그아웃
  + 매장 등록, 메뉴 등록, 자기 가게 조회
  + 게시글 삭제

## 주요 코드
### User
 + 매장정보조회 및 검색
    1. ID값을 이용하여 매장 정보들을 가져와 배열에 담아서 마커에 더해줍니다.
    2. 카카오지도 API를 이용하여 마커를 지도에 표시합니다.
    3. 검색란에 매장명 입력 시 검색한 단어를 포함하는 모든 매장을 보여줍니다.
    
            map.jsp 스크립트 일부
        
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

             MapDao 일부분
    
            public List<Map> search(String name){
            String sql = "SELECT * FROM map WHERE name like '%" + name +"%' ";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Map>(Map.class));
             }
  
  4. 매장을 검색했을 때 그 매장의 메뉴도 불러와서 조회할 수 있도록 하는 과정에서 어려움이 있었지만 스크립트에 배열과 for문을 이용해서 해결하였습니다.
  
                          Map.jsp 일부

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

			Map.jsp 스크립트 일부

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
  
+ 웨이팅 조회 및 등록
  1. 매장명과 로그인 되어있는 ID 세션을 이용하여 정보를 가져왔습니다.
  2. 순서나 내 앞의 팀들은 DB에 저장된 regDate를 비교하여 계산하였습니다.
  3. 웨이팅 첫번째 순서일 때 자동으로 웨이팅 취소를 시키기 위해 먼저 현재시간에서 5분뒤의 시간을 DB에 저장시ㅣ켰습니다.
  4. DB에 저장한 시간과 현재시간을 비교해 현재시간이 더 크다면 웨이팅을 삭제시켜 주었습니다.
  
		       WaitingController 일부

			@GetMapping("/controller/get_waiting")
			public String getWaitingForm(Model model, HttpSession session) throws ParseException {
				String userId = (String) session.getAttribute("userId");
				// 웨이팅을 하지 않았을 때
				if (waitingService.findWaitingById(userId).get(0).getBarName() == "없음") {
					List<Waiting> noWaiting = waitingService.findWaitingById(userId);
					model.addAttribute("frontCount", "0");
					model.addAttribute("allCount", "0");
					model.addAttribute("waiting", noWaiting);
					model.addAttribute("shopTel", "-");
					return "waiting/get_waiting";
				}

				// 웨이팅 해둔 상태 일때
				List<Waiting> waitingList = waitingService.findAllWaiting(
						waitingService.findWaitingById(userId).get(0).getBarName());
				long allCount = 0;
				long frontCount = 0;

				try {
					Date day1;
					Date day2;
					day2 = format
							.parse(waitingService.findWaitingById(userId).get(0).getRegDate());
					for (int i = 0; i < waitingList.size(); i++) {
						allCount++; // 특정 매장에대한 나 포함 모든 웨이팅 수
						day1 = format.parse(waitingList.get(i).getRegDate());
						int compare = day1.compareTo(day2);
						if (compare < 0) {
							frontCount++; // 내 앞의 웨이팅 수
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

				model.addAttribute("frontCount", frontCount);
				model.addAttribute("allCount", allCount);
				model.addAttribute("waiting", waitingService.findWaitingById(userId));
				model.addAttribute("shopTel",
						shopService.findAllByShopName(
								waitingService.findWaitingById(userId).get(0).getBarName()).get(0).getShopTel());

				// 내 앞 대기팀이 0팀 일때
				if (frontCount == 0) {
					// 언제까지오라는 시간 부여받지 않았을때 or waitingStartTime이 0 일때
					if (waitingService.findWaitingById(userId).get(0).getWaitingStartTime()
							.equals("0")) {
						SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
						Date nowDate = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(nowDate);
						cal.add(Calendar.MINUTE, 1); // 웨이팅 타이머 1 -> 1분
						String outputText = outputFormat.format(cal.getTime());

						waitingService.addWaitingTime(userId, outputText);
						String waitingTime = waitingService.findWaitingById(userId).get(0)
								.getWaitingStartTime();
						model.addAttribute("msg", waitingTime + " 까지 와주시기 바랍니다. (자동취소 예정)");
						return "waiting/get_waiting";
					} else {
						// 언제까지 오라는 시간을 부여 받은 상황
						SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
						Date nowDate = new Date();
						Calendar cal = Calendar.getInstance();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

						String waitingTime = waitingService.findWaitingById(userId).get(0)
								.getWaitingStartTime();

						cal.setTime(formatter.parse(waitingTime));
						int yearDate = Integer.parseInt(df.format(nowDate.getTime()).split("-", 0)[0]);
						int monthDate = Integer.parseInt(df.format(nowDate.getTime()).split("-", 0)[1]);
						int dateDate = Integer.parseInt(df.format(nowDate.getTime()).split("-", 0)[2]);

						cal.set(yearDate, monthDate-1, dateDate);

						model.addAttribute("msg", waitingTime + " 까지 와주시기 바랍니다. (자동취소 예정)");
						if ( nowDate.after(cal.getTime()) ) { 
							waitingService.deleteWaiting(userId);
							return "redirect:/controller/get_waiting";
						}
					}
				}

				return "waiting/get_waiting";
			}
  
+ 게시글 좋아요 기능
  1. DB에 좋아요 테이블을 만들고 눌러졌는지 체크하는 ikeCheck  열을 만들어 true, false로 구분하였습니다.
  2. 좋아요 클릭 시 false 라면 true 로 바꿔줌과 동시에 게시글 총 좋아요 개수를 1 더하여 업데이트 해주었습니다.
  3. A 아이디로 좋아요를 눌러둔 상태에서 B가 로그인 했을 때, 좋아요가 눌러져있는 상황에 어려움을 겪었습니다.
  4. 우선, controller 에서 Like 테이블의 likeCheck를 이용하여 for문과 if문을 사용해서 false인지 true인지 확인하였습니다.
  5. false라면 게시글의 좋아요 이미지 모두를 흰색하트로 변경하는 작업을 추가하면서 해결할 수 있었습니다. (true 라면 반대로 빨간하트 추가)
  
      
		    BoardController 일부

		    @GetMapping("/board/home")
		    public String list(HttpSession session, Model model, Board board) {
				String userId = (String) session.getAttribute("userId");
				String bnsNum = (String) session.getAttribute("bnsNum");

				if (session.getAttribute("userId") == null && session.getAttribute("dbOwner") == null) {
					model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
					model.addAttribute("url", "../login");
					return "alert/alert";
			}

			for (int i = 0; i < service.read(bnsNum).size(); i++) {
				long boardNum = service.read(bnsNum).get(i).getNumber();
				if (!service.findLikes(userId, boardNum).get(0).getUserId().equals("없음")) {		
					if (service.findLikes(userId, boardNum).get(0).getLikeCheck().equals("false")) {
						service.updateLikeImg(boardNum, "dislikeheart");
					} else {
						service.updateLikeImg(boardNum, "likeheart");
					}
				} else {
					service.updateLikeImg(boardNum, "dislikeheart");
				}
			}

			model.addAttribute("board", service.read(bnsNum));

			return "board/home";
			}
                                                    
		  @GetMapping("/board/likes")
			public String getLikes(HttpSession session, Model model, Board board, HttpServletRequest request) {
				String userId = (String) session.getAttribute("userId");

				// 만약 Likes 테이블에 id, number가 동일한 정보가 없으면 만들어주기 아니면 밑에꺼 실행
				if (service.findLikes(userId, board.getNumber()).get(0).getUserId().equals("없음")
						&& service.findLikes(userId, board.getNumber()).get(0).getNumber() == -1) {
					Likes likes = new Likes();
					String result = "false";
					likes.setUserId(userId);
					likes.setLikeCheck(result);
					likes.setNumber(board.getNumber());
					service.likeuser(likes);
					service.checkUpdate(userId, board.getNumber(), "true");
					service.likecountPlus(board.getLikecount(), board.getNumber());
					service.updateLikeImg(board.getNumber(), "likeheart");
				} else { // DB에 아이디랑 게시글번호가 동일한 정보가 있다면 true, false를 비교한다
					if (service.findLikes(userId, board.getNumber()).get(0).getLikeCheck().equals("false")) { // 좋아요를 누르지 않은 상태
						service.checkUpdate(userId, board.getNumber(), "true");
						service.likecountPlus(board.getLikecount(), board.getNumber());
						service.updateLikeImg(board.getNumber(), "likeheart");
					} else {
						service.checkUpdate(userId, board.getNumber(), "false");
						service.likecountDown(board.getLikecount(), board.getNumber());
						service.updateLikeImg(board.getNumber(), "dislikeheart");
					}
				}

				return "redirect:/board/home";
			}
                                                    
                                                    
## 구현 화면
  ### 
