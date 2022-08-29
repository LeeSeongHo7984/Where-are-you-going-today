package com.varxyz.wgt.owner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.wgt.owner.doamin.Owner;
import com.varxyz.wgt.owner.service.OwnerService;
import com.varxyz.wgt.owner.serviceImpl.OwnerServiceImpl;

@Controller
public class OwnerController {
	// 점주 서비스 객체 생성
	OwnerService ownerService = new OwnerServiceImpl();
	
	// 점주가입
	@GetMapping("/addOwner")
	public String addOwnerForm() {
		
		return "owner/addOwner";
	}
	
	@PostMapping("/addOwner")
	public String addOwner(Owner owner, Model model) {
		
		Owner dbOwner = new Owner();
		dbOwner = ownerService.findAllOwner(owner.getOwnerId());
		
//		// 사업자 중복검사 (boolean 타입 형식)
//		if(!ownerService.duplicationBn(owner.getBnumber())) {
//			model.addAttribute("msg", "사업자 번호가 있습니다!!");
//			
//			return "alert/back";
//		}
		
		// 객체는 null로 비교하면 됨
		if(dbOwner.getOwnerId() == null) {
			ownerService.addOwner(owner);
			OwnerService.context.close();
			
			return "owner/successAddOwner";
			
		}
		
		model.addAttribute("msg", "중복된 아이디 입니다!!");
		model.addAttribute("url", "addOwner");
		
		return "error/error";
		
	}
	
	// 점주 정보 가져오기
	@GetMapping("/modifyOwner") 
	public String findAllOwnerForm(HttpServletRequest request, HttpSession session, Model model) {
		
		Owner ownerList = new Owner();
		ownerList = ownerService.findAllOwner((String)session.getAttribute("ownerId"));
		model.addAttribute("ownerList", ownerList);
		
		return "owner/modifyOwner";
	}
	
	// 점주 정보 수정
	@PostMapping("/modifyOwner")
	public String modifyOwnerForm(Owner owner, HttpServletRequest request, HttpSession session, Model model) {
		
		ownerService.modifyOwner(owner);
		
		return "owner/successModifyOwner";
	}
	
	// 점주 탈퇴
	@GetMapping("/deleteOwner")
	public String deleteOwnerForm(HttpServletRequest request, HttpSession session, Model model) {
		
		return "login/ownerLogin";
	}
	
	@PostMapping("/deleteOwner")
	public String delete(HttpServletRequest request, HttpSession session, Model model) {
		
		ownerService.delete((String)session.getAttribute("ownerId"));	// 세션 ownerId를 가져와서 삭제
		
		return "owner/deleteOwner";
	}
}
