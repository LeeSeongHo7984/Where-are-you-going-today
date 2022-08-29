package com.varxyz.wgt.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
	public String addUser(@RequestParam("file") MultipartFile file ,HttpServletRequest request, HttpSession session,  Model model) {
		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메소드
		long size = file.getSize(); // 파일 사이즈
		
		System.out.println("파일명 : " + fileRealName);
		System.out.println("파일크기 : " + size);
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
		String uploadFolder = "C:\\Users\\tjdgh\\LSH\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\user\\img";
		
		
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
		user.setAddr(request.getParameter("addr"));
		user.setImgName(request.getParameter("imgName"));
		
		List<User> userList = new ArrayList<User>();
		userList = userService.inquiryUser(user.getUserId());
		
		if(user.getUserId().equals(userList.get(0).getUserId())) {
			model.addAttribute("msg", "중복된 아이디 입니다!!");
			model.addAttribute("url", "addUser");
			
			return "error/error";
		} 
		
			// 생성되기 전에 위에서 중복검사를 하고 유저를 여기서 추가해야함
			userService.addUser(user, uniqueName);
			UserService.context.close();
			
			return "login/login";
}

	// 회원정보 가져오기
	@GetMapping("/modifyUser")
	public String findAllUserForm(MultipartFile file, HttpServletRequest request, HttpSession session, Model model) {
		
		List<User> userList = new ArrayList<User>();
		userList = userService.inquiryUser((String)session.getAttribute("userId"));	// 세션을 가져옴
		model.addAttribute("userList", userList);
		System.out.println(userList.get(0).getImgName());

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
			user.setImgName(fileRealName);
			userService.modifyUser(user, fileRealName);
			
			return "user/modifyUser";
			
		}
		
		System.out.println("파일명 : " + fileRealName);
		System.out.println("파일크기 : " + size);
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
		String uploadFolder = "C:\\Users\\tjdgh\\LSH\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\user\\img"; 
		
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
		user.setPasswd(request.getParameter("phone"));
		user.setAddr(request.getParameter("addr"));
		user.setImgName(request.getParameter("imgName"));
		System.out.println("2" + request.getParameter("imgName"));
		
		userService.modifyUser(user, uniqueName);
		
		return "user/successModifyUser";
	}

	// 회원 탈퇴
	@GetMapping("/deleteUser")
	public String deleteUserForm(HttpServletRequest request, HttpSession session, Model model) {
		
		return "login/login";
	}
	
	@PostMapping("/deleteUser")
	public String delete(HttpServletRequest request, HttpSession session, Model model) {
		
		userService.delete((String)session.getAttribute("userId"));	// 세션 userId 가져와서 삭제
		
		return "user/deleteUser";
	}

}
