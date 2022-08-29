package com.varxyz.wgt.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.wgt.shop.domain.Menu;
import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller
public class AddMenuController {

	ShopService service = new ShopServiceImpl();


	@GetMapping("shop/addMenu")
	public String addMenuGo(Model model, HttpSession session) {
		String bNum = (String)session.getAttribute("bnsNum");
		
		
		if(service.findShopMenuByBnsNum(bNum).size() > 9) {
			model.addAttribute("msg", "메뉴 등록은 최대 10개 까지만 가능합니다.");
			model.addAttribute("url", "viewMyShop");
			return "alert/alert";
		}
		model.addAttribute("menuListSize",service.findShopMenuByBnsNum(bNum).size());
		return "shop/view/addMenu";
	}

	@PostMapping("shop/addMenu")
	public String addMenuForm(@RequestParam("menu_img") MultipartFile file,
							  @RequestParam("menu_name") String menuName,
							  @RequestParam("menu_price") int price,
							  @RequestParam("menu_intro") String menuIntro,
							  Model model, HttpSession session){
		String bNum = (String)session.getAttribute("bnsNum");
		
		if(!service.shopFindMenuCheck( menuName, bNum )) {
			model.addAttribute("msg", "중복된 메뉴이름은 사용하실 수 없습니다.");
			return "alert/back";
		}
		
		if(service.findShopMenuByBnsNum(bNum).size() > 9) {
			model.addAttribute("msg", "메뉴 등록은 최대 10개 까지만 가능합니다.");
			model.addAttribute("url", "viewMyShop");
			return "alert/alert";
		}

		Menu menu = new Menu();

		menu.setBusinessNumber(bNum);
		menu.setMenuName(menuName);
		menu.setMenuPrice(price);
		menu.setMenuIntro(menuIntro);

		String fileRealName = file.getOriginalFilename(); // 실제 파일 명을 알수있는 메소드
		long size = file.getSize(); // 파일 사이즈

		// 사용자가 이미지를 업로드 하지 않았을 경우 예외 처리
		if (fileRealName == null || fileRealName.length() == 0) {

			model.addAttribute("msg","메뉴 사진을 등록해주세요!");
			return "alert/back";

		}

		System.out.println("파일명 : " + fileRealName);
		System.out.println("용량 크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함

		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());

		String uploadFolder = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img";

		// 집 경로
//		String uploadFolder = "C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp";

		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */

		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");

		String uniqueName = uuids[0];
		System.out.println("생성된 고유 문자열 : " + uniqueName );
		session.setAttribute("tempShopImg", uniqueName);
		System.out.println("확장자명 : " + fileExtension);
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); // 적용 후
		menu.setMenuImg(uniqueName);
		try {
			file.transferTo(saveFile); // 실제 파일 저장메소드(filewriter 작업을 손쉽게 한방에 처리해준다.
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		service.addMenu(menu);
		model.addAttribute("msg", "메뉴 등록이 완료 되었습니다.");
		model.addAttribute("url", "addMenu");
		return "alert/alert";
	}
}
