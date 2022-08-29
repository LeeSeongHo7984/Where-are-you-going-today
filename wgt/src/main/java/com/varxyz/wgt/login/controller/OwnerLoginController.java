package com.varxyz.wgt.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.wgt.login.service.OwnerLoginService;
import com.varxyz.wgt.login.serviceImpl.OwnerLoginServiceImpl;
import com.varxyz.wgt.owner.doamin.Owner;
import com.varxyz.wgt.owner.service.OwnerService;
import com.varxyz.wgt.owner.serviceImpl.OwnerServiceImpl;
import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller
public class OwnerLoginController {
	OwnerLoginService ownerLoginService = new OwnerLoginServiceImpl();

	OwnerService ownerService = new OwnerServiceImpl();

	// 점주 로그인
	@GetMapping("/ownerLogin")
	public String ownerForm(HttpSession session) {
		session.invalidate();	// 세션 단절

		return "login/ownerLogin";
	}

	@PostMapping("/ownerLogin")
	public String ownerLogin(Owner owner, HttpSession session, HttpServletRequest request,
							Model model) {

		// 수정 전 코드
//		List<Owner> ownerList = new ArrayList<Owner>();
//		ownerList = ownerService.findAllOwner(owner.getOwnerId());

		/*
		 *  findAllOwner 기능 = 점주 ID로 점주 '1'명의 정보를 모두 불러 오는것
		 *  그렇기 때문에 List<Owner>로 dao에서 query로 받기보다는 queryObject로 그냥 하나의 Owner 객체로 받아오는것이 더 낫다
		 *  수정한점 : OwnerDao.class에서 findAllOwner기능 중 query -> queryObject, return 타입 List<Owner> -> Owner
		 */


		// 수정 된 코드

		Owner dbOwner = new Owner();
		dbOwner = ownerService.findAllOwner(owner.getOwnerId());
		session.setAttribute("dbOwner", dbOwner);

		// 수정 전 코드
//		if(owner.getOwnerId().equals(ownerList.get(0).getOwnerId())) {
//			if(owner.getPasswd().equals(ownerList.get(1).getPasswd())) {
//				if(owner.getBnumber().equals(ownerList.get(2).getBnumber())) {
//					return "login/successOwnerLogin";
//				}
//			}
//		}

		/*
		 *  로그인시 위 로직에서 Index out of bounds 에러가 발생하는데 그 이유는 원래 기능에서 List로 불러온것은 맞지만
		 *  [Owner(객체)] 이렇게 배열안에 하나의 객체만 불러온것이기 때문에  owner.getPasswd().equals(ownerList.get(1).getPasswd()) 이 문단처럼
		 *  실제는 0번째 밖에없는 배열이지만 1번째 배열을 불러오라고 했기때문에 오류가 뜨는것이다. 만약 List 그대로 사용할려고 한다면 index를 0으로 바꿔줘야한다.
		 */

		// 수정 후 코드

		// &&로 엮어서 조건을 구사 할 수도 있다.
		if(owner.getOwnerId().equals(dbOwner.getOwnerId()) &&
		   owner.getPasswd().equals(dbOwner.getPasswd())) {

			ShopService shopService = new ShopServiceImpl();

			session.setAttribute("bnsNum", shopService.findShopByOwnerId(owner.getOwnerId()).getBusinessNumber());
			session.setAttribute("ownerId", request.getParameter("ownerId"));
			return "redirect:/add_shop";
		}

		System.out.println("로그인 실패");

		model.addAttribute("msg", "회원정보가 틀렸습니다");
		model.addAttribute("url", "ownerLogin");

		return "error/error";


//		try {
//			Owner ownerList = new Owner();
//			ownerList = ownerLoginService.ownerLogin(owner.getOwnerId());
//
//			session.setAttribute("ownerList", ownerList);
//
//			if(owner.getOwnerId().equals(ownerList.getOwnerId()) && owner.getPasswd().equals(ownerList.getPasswd())) {
////				System.out.println(owner.getOwnerId());
////				System.out.println(ownerList.getOwnerId());
//				session.setAttribute("ownerId", owner.getOwnerId());
//
//			return "login/successOwnerLogin";
//			}
//
//		} catch (EmptyResultDataAccessException e) {
////			e.printStackTrace(); // 무슨 에러가 나는지 콘솔창에서 알려줌
//			System.out.println(owner.getOwnerId());
////			System.out.println(ownerList.getOwnerId());
//			model.addAttribute("msg", "아이디를 다시 확인하세요!!");
//			model.addAttribute("url", "ownerLogin");
//
//			return "error/error";
//		}
//
//		model.addAttribute("msg", "비밀번호를 다시 확인하세요!!");
//		model.addAttribute("url", "ownerLogin");
//
//		return  "error/error";
	}

	// 로그아웃
	@GetMapping("/ownerLogOut")
	public String ownerLogOutForm() {

		return "logOut/ownerLogOut";
	}

	@PostMapping("ownerLogOut")
	public String ownerLogOut(HttpSession session) {
		session.invalidate(); // 세션단절

		return "login/ownerLogin";
	}


}
