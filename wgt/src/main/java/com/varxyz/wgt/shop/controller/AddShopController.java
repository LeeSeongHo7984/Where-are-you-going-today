package com.varxyz.wgt.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.wgt.shop.domain.Menu;
import com.varxyz.wgt.shop.domain.Shop;
import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller
public class AddShopController {
	Shop shop = new Shop();
	List<Menu> menuList = new ArrayList<>();
	List<String> tempImgList = new ArrayList<>();
	// 첫번째 폼
	@GetMapping("/add_shop")
	public String addShopGo(HttpSession session, Model model) {
		menuList.removeAll(menuList);
		String ownerId = (String)session.getAttribute("ownerId");
		if (ownerId == null) {
			model.addAttribute("msg", "로그인 후 이용해주세요");
			model.addAttribute("url", "login");
			return "alert/alert";
		}
		ShopService service = new ShopServiceImpl();
			if(service.findShopByOwnerId(ownerId).getShopName() == null) {
				return "shop/addShop";							
			}
				
			return "redirect:/shop/viewMyShop";
	}
	
	@GetMapping("/add_shop2")
	public String addShop2(Model model) {
		
		model.addAttribute("msg", "잘못된 접근입니다.");
		
		return "alert/back";
	}
	
	// 첫번째 폼 작성 후 두번째 폼 이동
	@PostMapping("/add_shop2")
	public String addShop2Form(@RequestParam("bns_num") String bnsNum,
							   @RequestParam("shop_name") String shopName,
							   @RequestParam("shop_address1") String shopPostCode,
							   @RequestParam("shop_address2") String shopAddress,
							   @RequestParam("shop_address3") String shopDetailAddress,
							   @RequestParam("shop_address4") String shopExtraAddress,
							   HttpSession session, Model model){
		
		// 빈값 입력시 예외 처리
		if(bnsNum.trim().isEmpty() ||
		   shopName.trim().isEmpty() ||
		   shopPostCode.trim().isEmpty() ||
		   shopAddress.trim().isEmpty() ||
		   shopDetailAddress.trim().isEmpty() ||
		   shopExtraAddress.trim().isEmpty()) {
		
			model.addAttribute("msg", "빈값은 입력하실 수 없습니다!");
			return "alert/back";
		}
		
		ShopService service = new ShopServiceImpl();
		
		if(service.findShopByBnsNum(bnsNum).getBusinessNumber() != null) {
			model.addAttribute("msg", "중복된 사업자 번호입니다.");
			return "alert/back";
		}
		
		session.setAttribute("bnsNum", bnsNum);
		
		shop.setBusinessNumber(bnsNum);	
		shop.setShopName(shopName);
		shop.setShopPostCode(shopPostCode);
		shop.setShopAddress(shopAddress);
		shop.setShopDetailAddress(shopDetailAddress);
		shop.setShopExtraAddress(shopExtraAddress);
		shop.setOwnerId((String)session.getAttribute("ownerId"));
		
		return "shop/addShop2";
	}
	
	@GetMapping("/add_shop3")
	public String addShop3(Model model) {
		
		model.addAttribute("msg", "잘못된 접근입니다.");
		
		return "alert/back";
	}
	
	// 두번째 폼 작성 후 세번째 폼 이동
	@PostMapping("/add_shop3")
	public String addShop3Form(@RequestParam("shop_hour1") String shopHour1,
							   @RequestParam("shop_hour2") String shopHour2,
							   @RequestParam("shop_table") String shopTables,
							   @RequestParam("shop_max_people") String shopMaxPeople,
							   @RequestParam("shop_tel1") String shopTel1,
							   @RequestParam("shop_tel2") String shopTel2,
							   @RequestParam("shop_tel3") String shopTel3,
							   HttpSession session, Model model) {
		// 빈값 입력시 예외 처리
		if(shopTel2.trim().isEmpty() || shopTel3.trim().isEmpty()){
			model.addAttribute("msg", "빈값은 입력하실 수 없습니다!");
			return "alert/back";
		}
		
		String shopHour = shopHour1 + " ~ " + shopHour2;
		shop.setShopHours(shopHour);
		shop.setShopTables(shopTables);
		shop.setShopMaxPeoples(shopMaxPeople);
		String shopTel = shopTel1 + "-" + shopTel2 + "-" + shopTel3;
		shop.setShopTel(shopTel);
		return "shop/addShop3";
	}
	
	//3번째 폼 작성 후 4번째 폼 이동 이 폼에서는 가게의 사진을 업로드하는 과정이 이루어짐
	@PostMapping("/add_shop4")
	public String addShop4Form(@RequestParam("shop_img") MultipartFile file, Model model, HttpSession session) {
		
		
		String fileRealName = file.getOriginalFilename(); // 실제 파일 명을 알수있는 메소드
		long size = file.getSize(); // 파일 사이즈
		
		// 사용자가 이미지를 업로드 하지 않았을 경우 예외 처리
		if (fileRealName == null || fileRealName.length() == 0) {

			model.addAttribute("msg","가게 사진을 등록해주세요!");
			return "alert/back";
			
		}
		
		System.out.println("파일명 : " + fileRealName);
		System.out.println("용량 크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		
		// resources에 temp 폴더 절대 경로 입력 String uploadFolder = "";  
		// 점주가 등록 취소 할 수 있기때문에 우선은 temp폴더에 임시 저장
		String uploadFolder = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp";
		
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
		shop.setShopImg(uniqueName);
		System.out.println("확장자명 : " + fileExtension);
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); // 적용 후
		
		try {
			file.transferTo(saveFile); // 실제 파일 저장메소드(filewriter 작업을 손쉽게 한방에 처리해준다.
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return "shop/addShop4";
	}
	
	@GetMapping("/add_shop4")
	public String addShop4(Model model) {
		
		model.addAttribute("msg", "잘못된 접근입니다.");
		
		return "alert/back";
	}
	
	@GetMapping("add_shop5")
	public String addShop5Go(Model model, HttpSession session) {
		if (menuList.size() == 0) {
			
			model.addAttribute("msg", "최소 1개 이상의 메뉴가 등록되어야 합니다!");
			return "alert/back";
		}
		
		ShopService service = new ShopServiceImpl();
		service.addShop(shop);
		for (Menu menuItem : menuList) {
			service.addMenu(menuItem);
		}
		for (String img : (List<String>)session.getAttribute("tempImgList")) {
			File tempImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
			File newImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img\\" + img + ".jpg");
			
			// 집 경로
			
//			File tempImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
//			File newImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img\\" + img + ".jpg");
			
			try {
				FileUtils.moveFile(tempImg, newImg);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		session.removeAttribute("tempImgList");
		String img = (String)session.getAttribute("tempShopImg");
		File tempImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
		File newImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\shop_Img\\" + img + ".jpg");
		
		// 집 경로
		
//		File tempImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
//		File newImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\shop_Img\\" + img + ".jpg");
		
		try {
			FileUtils.moveFile(tempImg, newImg);
		}catch (IOException e) {
			e.printStackTrace();
		}
		session.removeAttribute("tempShopImg");
		return "shop/addShop5";
	}
	
	@PostMapping("/add_shop5")
	public String addShop5Form(@RequestParam("menu_img") MultipartFile file,
							   @RequestParam("menu_name") String menuName,
							   @RequestParam("menu_price") String menuPrice,
							   @RequestParam("menu_intro") String menuIntro,
							   Model model, HttpSession session) {
		Menu menu = new Menu();
		
		// 빈값 입력시 예외 처리
		if(menuName.trim().isEmpty() ||
		   menuPrice.trim().isEmpty() ||
		   menuIntro.trim().isEmpty())
		   {
		
			model.addAttribute("msg", "빈값은 입력하실 수 없습니다!");
			return "alert/back";
		}
		
		for (Menu checkMenu : menuList) {
			if(checkMenu.getMenuName().equals(menuName)) {
				model.addAttribute("msg", "중복된 이름의 메뉴는 등록하실수 없습니다.");
				return "alert/back";
			}
		}
		
		menu.setMenuName(menuName);
		menu.setMenuPrice(Integer.parseInt(menuPrice));
		menu.setMenuIntro(menuIntro);
		menu.setBusinessNumber(shop.getBusinessNumber());
		
		String fileRealName = file.getOriginalFilename(); // 실제 파일 명을 알수있는 메소드
		long size = file.getSize(); // 파일 사이즈
		
		// 사용자가 이미지를 업로드 하지 않았을 경우 예외 처리
		if (fileRealName == null || fileRealName.length() == 0) {
			menuList.add(menu);
			model.addAttribute("msg","메뉴 사진을 등록해주세요!");
			return "alert/back";
		}
		
		System.out.println("파일명 : " + fileRealName);
		System.out.println("용량 크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		
		// resources에 temp 폴더 절대 경로 입력 String uploadFolder = "";  
		// 점주가 등록 취소 할 수 있기때문에 우선은 temp폴더에 임시 저장
		String uploadFolder = "C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp";
		
		// 집 경로
//		String uploadFolder = "C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp";
		
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있다. 
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
		
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유 문자열 : " + uniqueName );
		// 등록 도중 등록 취소시 map/map으로 이동 후에 temp에 올렸던 것들 전부 삭제를 위한 세션 생성
		tempImgList.add(uniqueName);
		session.setAttribute("tempImgList", tempImgList);
		menu.setMenuImg(uniqueName);
		System.out.println("확장자명 : " + fileExtension);
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); // 적용 후
		
		try {
			file.transferTo(saveFile); // 실제 파일 저장메소드(filewriter 작업을 손쉽게 한방에 처리해준다.
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		// 올릴 수 있는 최대 메뉴 사이즈 현재 10개.
		if (menuList.size() > 9) {
			menuList.add(menu);
			ShopService service = new ShopServiceImpl();
			service.addShop(shop);
			for (Menu menuItem : menuList) {
				service.addMenu(menuItem);
			}
			for (String img : (List<String>)session.getAttribute("tempImgList")) {
				File tempImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
				File newImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img\\" + img + ".jpg");
				
				// 집 경로
				
//				File tempImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
//				File newImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img\\" + img + ".jpg");
				
				try {
					FileUtils.moveFile(tempImg, newImg);
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			session.removeAttribute("tempImgList");
			String img = (String)session.getAttribute("tempShopImg");
			File tempImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
			File newImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\shop_Img\\" + img + ".jpg");
			
			// 집 경로
			
//			File tempImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
//			File newImg = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\shop_Img\\" + img + ".jpg");
			
			try {
				FileUtils.moveFile(tempImg, newImg);
			}catch (IOException e) {
				e.printStackTrace();
			}
			session.removeAttribute("tempShopImg");
			return "shop/addShop5";
		}else {
			menuList.add(menu);
			model.addAttribute("menuListSize", menuList.size());
		}
		
		return "shop/addShop4";
		
	}
	
 }
