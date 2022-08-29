package com.varxyz.wgt.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.wgt.board.domain.Board;
import com.varxyz.wgt.board.service.BoardService;
import com.varxyz.wgt.board.service.BoardServiceImpl;
import com.varxyz.wgt.user.service.UserService;
import com.varxyz.wgt.user.serviceImpl.UserServiceImpl;

@Controller
public class MypageController {
	BoardService service = new BoardServiceImpl();
	UserService userService = new UserServiceImpl();

	// 자기가 작성한 게시글만 가져오기
	@GetMapping("/board/mypage")
	public String post(HttpSession session, Model model, HttpServletRequest request, Board board) {
//		System.out.println(session.getAttribute("userId")+"님의 마이페이지");

		// 로그인 안되었을때 유효성 검사
		if (session.getAttribute("userId") == null && session.getAttribute("dbOwner") == null) {
			model.addAttribute("msg", "로그인 후 이용해주세요");
			model.addAttribute("url", "../login");
			return "alert/alert";
		}

		String userId = (String) session.getAttribute("userId");
		String bnsNum = (String) session.getAttribute("bnsNum");

		// 유저아이디로 자기 게시글만 조회
		List<Board> myBoard = new ArrayList<Board>();
		for (int i = 0; i < service.read(bnsNum).size(); i++) {
			if (service.read(bnsNum).get(i).getUserId().equals(userId)) {
				myBoard.add(service.read(bnsNum).get(i));
			}
		}

		model.addAttribute("userId", userId);
		model.addAttribute("mypageboard", myBoard);

		return "/board/mypage";
	}

	// 게시글 수정
	@GetMapping("/board/update")
	public String updateget(@RequestParam("number") int number, MultipartFile file, HttpServletRequest request,
			HttpSession session, Model model, Board board) {
		model.addAttribute("board", service.searchByNumber(number));
//		System.out.println(service.searchByNumber(number));
		return "board/update";
	}

	@PostMapping("/board/update")
	public String update(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메서드
		long size = file.getSize();

		Board board = new Board();
		board.setContent(request.getParameter("content"));
		board.setTitle(request.getParameter("title"));
		board.setImgname(request.getParameter("imgname"));
		int boardNum = Integer.parseInt(request.getParameter("number"));
		board.setNumber(boardNum);
		board.setRegDate(service.searchByNumber(board.getNumber()).getRegDate());

		// 사용자가 이미지를 업로드 하지 않았을 경우 예외 처리
		if (fileRealName == null || fileRealName.length() == 0) {
			board.setImgname(fileRealName);
			service.update(board, request.getParameter("imgname"));

			model.addAttribute("msg", "게시글 수정이 완료되었습니다!");

			return "board/mypage";
		}

//		String imgname = board.getImgname();
		model.addAttribute("board", board);

		System.out.println("파일명 : " + fileRealName);
		System.out.println("용량크기(byte) : " + size);

		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
		String uploadFolder = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\board\\img\\upload";

		UUID uuid = UUID.randomUUID();
		String[] uuids = uuid.toString().split("-");
		String uniqueName = uuids[0];

		System.out.println(uuid.toString());
		System.out.println("생성된 고유 문자열 : " + uniqueName);
		System.out.println("확장자명 : " + fileExtension);

		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); // 적용 후

		try {
			file.transferTo(saveFile); // 파일 저장메소드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.update(board, uniqueName);
		return "redirect:/board/mypage";
	}

	// 게시글 삭제
	@GetMapping("/board/delete")
	public String deleteGet(@RequestParam("number") int number, Model model, HttpSession session, Board board) {
		board = service.searchByNumber(number);
		String imgname = board.getImgname(); // board 선언, imgname-board 객체변환

		String filePath = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\board\\img\\upload"
				+ imgname + ".jpg";
		File file = new File(filePath);

		System.out.println(number);
		System.out.println(file);
		System.out.println(service.searchByNumber(number));
		// 파일이 존재하는지 체크 존재할경우 true, 존재하지않을경우 false
		if (file.exists()) {
			// 파일을 삭제합니다.
			session.removeAttribute(imgname);
			file.delete();
			System.out.println("파일을 삭제하였습니다.");
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		service.delete(number, imgname);

		boolean ownerchk = false;
		if (session.getAttribute("dbOwner") == null) {
			model.addAttribute("ownerchk", ownerchk);
			return "redirect:/board/mypage";
		} else {
//			ownerchk = true;
			model.addAttribute("ownerchk", ownerchk);
			return "redirect:/board/home";
		}
	}

}