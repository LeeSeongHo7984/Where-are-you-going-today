package com.varxyz.wgt.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.wgt.board.domain.Board;
import com.varxyz.wgt.board.domain.Likes;
import com.varxyz.wgt.board.service.BoardService;
import com.varxyz.wgt.board.service.BoardServiceImpl;
//import com.varxyz.wgt.owner.service.OwnerService;
//import com.varxyz.wgt.owner.serviceImpl.OwnerServiceImpl;
import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller
public class BoardController {
	BoardService service = new BoardServiceImpl();
	ShopService service2 = new ShopServiceImpl();
//	OwnerService service3 = new OwnerServiceImpl();

	// 게시판 화면
	@GetMapping("/board/home")
	public String list(HttpSession session, Model model, Board board) {
		String userId = (String) session.getAttribute("userId");
		String bnsNum = (String) session.getAttribute("bnsNum");
//		service2.findShopByBnsNum(bnsNum);
//		System.out.println(service2.findShopByBnsNum(bnsNum));
//		System.out.println(service2.findShopByBnsNum(bnsNum).getShopName());

		// 유저 로그인 세션
		if (session.getAttribute("userId") == null && session.getAttribute("dbOwner") == null) {
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			model.addAttribute("url", "../login");
			return "alert/alert";
		}

		// 좋아요 화면 유지 로직
		for (int i = 0; i < service.read(bnsNum).size(); i++) {
			long boardNum = service.read(bnsNum).get(i).getNumber();
			if (!service.findLikes(userId, boardNum).get(0).getUserId().equals("없음")) {
//				System.out.println(service.findLikes(userId, boardNum).get(0).getUserId());				
				if (service.findLikes(userId, boardNum).get(0).getLikeCheck().equals("false")) {
					service.updateLikeImg(boardNum, "dislikeheart");
				} else {
					service.updateLikeImg(boardNum, "likeheart");
				}
			} else {
				service.updateLikeImg(boardNum, "dislikeheart");
			}
		}
//			System.out.println(service.read(bnsNum));
		model.addAttribute("shop", service2.findShopByBnsNum(bnsNum).getShopName()); // 상점명 불러오기
		model.addAttribute("board", service.read(bnsNum));

		// 점주일 때만 삭제 보이게 하는 로직
		boolean ownerchk = false;
		if (session.getAttribute("dbOwner") == null) { // 유저일 때
			model.addAttribute("ownerchk", ownerchk); // OwnerLoginController에서 session.setAttribute("bnsNum", dbOwner.getBnumber()); 세팅 후 받아옴
		} else {
			ownerchk = true;
			model.addAttribute("ownerchk", ownerchk); // 점주일 때
		}
		return "board/home";
	}

	// 좋아요 기능
	@GetMapping("/board/likes")
	public String getLikes(HttpSession session, Model model, Board board, HttpServletRequest request) {
		String userId = (String) session.getAttribute("userId");

		if (session.getAttribute("userId") == null) {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "../login");
			return "alert/alert";
		}

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

	@PostMapping("/board/home")
	public String search(HttpSession session, Board board, Model model) {
		List<Board> list = service.search(board.getTitle());
		// 점주일 때만 삭제 보이게 하는 로직
		boolean ownerchk = false;
		if (session.getAttribute("dbOwner") == null) { // 유저일 때
			model.addAttribute("ownerchk", ownerchk); // OwnerLoginController에서 session.setAttribute("bnsNum", dbOwner.getBnumber()); 세팅 후 받아옴
		} else {
			ownerchk = true;
			model.addAttribute("ownerchk", ownerchk); // 점주일 때
		}
		model.addAttribute("list", list);
		return "board/search";
	}

	// 검색 화면
	@GetMapping("/board/search")
	public String searchlist(Model model) {
		return "board/search";
	}

	@PostMapping("/board/search")
	public String getsearchlist(Board board, Model model, HttpSession session) {
		List<Board> list = service.search(board.getTitle());
		// 점주일 때만 삭제 보이게 하는 로직
		boolean ownerchk = false;
		if (session.getAttribute("dbOwner") == null) { // 유저일 때
			model.addAttribute("ownerchk", ownerchk); // OwnerLoginController에서 session.setAttribute("bnsNum", dbOwner.getBnumber()); 세팅 후 받아옴
		} else {
			ownerchk = true;
			model.addAttribute("ownerchk", ownerchk); // 점주일 때
		}
		model.addAttribute("list", list);
		return "board/search";
	}
}