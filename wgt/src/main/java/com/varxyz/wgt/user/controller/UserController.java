package com.varxyz.wgt.user.controller;

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

import com.varxyz.wgt.user.domain.User;
import com.varxyz.wgt.user.service.UserService;
import com.varxyz.wgt.user.serviceImpl.UserServiceImpl;

@Controller("controller.userController")
public class UserController {
	// 유저 서비스 객체 생성
	UserService userService = new UserServiceImpl();
//	private Object imgName;
	
	// 회원가입
	@GetMapping("/addUser")
	public String addUserForm() {

		return "user/addUser";
	}

	@PostMapping("/addUser")
	public String addUser(@RequestParam("file") MultipartFile file ,HttpServletRequest request, HttpSession session,  Model model, String imgName ) {
		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메소드
		long size = file.getSize(); // 파일 사이즈
		
		if(fileRealName == null || fileRealName.length() == 0) {
			
			model.addAttribute("msg", "프로필 사진을 등록해 주세요!!");
			
			return "alert/back";
		}
		
		System.out.println("파일명 : " + fileRealName);
		System.out.println("파일크기 : " + size);
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());	// 실제 파일명을 알수있는 메소드
		String uploadFolder = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\user\\img";
		
		// 고유한 랜덤 문자생성 해서 db와 서버에 저장할 파일명을 새롭게 만들어 주는 코드
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유문자 : " + uniqueName);
		System.out.println("확장자 : " + fileExtension);
		
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension);
		try {
			file.transferTo(saveFile);	// 실제 파일 저장메소드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		User user = new User();
		
		user.setUserId(request.getParameter("userId"));
		user.setPasswd(request.getParameter("passwd"));
		user.setName(request.getParameter("name"));
		user.setSsn(request.getParameter("ssn"));
		user.setPhone(request.getParameter("phone"));
		user.setAddr1(request.getParameter("addr1"));
		user.setAddr2(request.getParameter("addr2"));
		user.setAddr3(request.getParameter("addr3"));
		user.setAddr4(request.getParameter("addr4"));
		user.setImgName(request.getParameter("imgName"));
		
		List<User> userList = new ArrayList<User>();
		userList = userService.inquiryUser(user.getUserId());

		// 리스트일 때는 size로 비교한다
		if(userList.size() > 0) {
			model.addAttribute("msg", "중복된 아이디 입니다!!");
			model.addAttribute("url", "addUser");
			
			return "error/error";
		}
		
			// 생성되기 전에 위에서 중복검사를 하고 유저를 여기서 추가해야함
			userService.addUser(user, uniqueName);
			UserService.context.close();
			
			return "user/successAddUser";
	}
	
	// 회원정보 보기
	@GetMapping("/userInfo")
	public String userInfo(MultipartFile file, HttpServletRequest request, HttpSession session, Model model) {
		
		List<User> userList = new ArrayList<User>();
		userList = userService.inquiryUser((String)session.getAttribute("userId"));	// 세션을 가져옴
		
		try {
			model.addAttribute("userList", userList);
			System.out.println(userList.get(0).getImgName());
			
			return "user/userInfo";
		} catch (IndexOutOfBoundsException e) {
			model.addAttribute("msg", "로그인후 이용하실 수 있습니다.");
			
			return "alert/back";
		}
		
	} 
	
	// 회원정보 가져오기
	@GetMapping("/modifyUser")
	public String findAllUserForm(MultipartFile file, HttpServletRequest request, HttpSession session, Model model) {
		
		List<User> userList = new ArrayList<User>();
		userList = userService.inquiryUser((String)session.getAttribute("userId"));	// 세션을 가져옴
		model.addAttribute("userList", userList);

		return "user/modifyUser";
	}
	
	// 회원정보 수정
	@PostMapping("/modifyUser") 
	public String modifyUserForm(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session, Model model) {

		User user = new User();
		
		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메소드
		long size = file.getSize(); // 파일 사이즈
		
		// 사용자가 이미지를 업로드 하지 않았을 경우 예외 처리
		if (fileRealName == null || fileRealName.length() == 0) {
			user.setImgName(request.getParameter("imgName"));
			userService.modifyUser(user, request.getParameter("imgName"));
			
			model.addAttribute("msg", "수정이 완료되었습니다!!");
			
			return "user/modifyUser";
			
		}
		
		System.out.println("파일명 : " + fileRealName);
		System.out.println("파일크기 : " + size);
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
		String uploadFolder = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\user\\img"; 
		
		// 고유한 랜덤 문자생성 해서 db와 서버에 저장할 파일명을 새롭게 만들어 주는 코드
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유문자 : " + uniqueName);
		System.out.println("확장자 : " + fileExtension);
		
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension);
		
		try {
			file.transferTo(saveFile);	// 실제 파일 저장메소드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		user.setUserId(request.getParameter("userId"));
		user.setPasswd(request.getParameter("passwd"));
		user.setName(request.getParameter("name"));
		user.setSsn(request.getParameter("ssn"));
		user.setPhone(request.getParameter("phone"));
		user.setAddr1(request.getParameter("addr1"));
		user.setAddr2(request.getParameter("addr2"));
		user.setAddr3(request.getParameter("addr3"));
		user.setAddr4(request.getParameter("addr4"));
		
		userService.modifyUser(user, uniqueName);
		
		return "user/successModifyUser";
	}

	// 회원 탈퇴
	@GetMapping("/deleteUser")
	public String deleteUserForm(HttpServletRequest request, HttpSession session, Model model) {
		
		return "login/login";
	}
	
	@PostMapping("/deleteUser")
	public String delete(String imgName, HttpServletRequest request, HttpSession session, Model model) {
		
		List<User> user = new ArrayList<User>();
		
		user = userService.inquiryUser((String)session.getAttribute("userId"));
		
		String dbImgName = user.get(0).getImgName();
		System.out.println(dbImgName);
		
		String filePath = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\user\\img\\" + dbImgName + ".jpg";
		
		File deleteFile = new File(filePath);
		System.out.println(deleteFile);
		// 파일 존재 여부 확인
		if(deleteFile.exists()) {
			// 있으면 삭제
			session.removeAttribute(dbImgName);
			deleteFile.delete();
			System.out.println("파일 삭제 완료");
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		userService.delete((String)session.getAttribute("userId"), dbImgName);	// 세션 userId 가져와서 삭제
		
		return "user/deleteUser";
	}

}
