package com.varxyz.wgt.map.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.wgt.map.domain.Map;
import com.varxyz.wgt.map.service.MapService;
import com.varxyz.wgt.map.service.MapServiceImpl;
import com.varxyz.wgt.shop.domain.Menu;
import com.varxyz.wgt.shop.domain.Shop;
import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller("controller.mapController")
public class MapController {
	MapService mapService = new MapServiceImpl();
	ShopService shopService = new ShopServiceImpl();
	
	
	  @GetMapping("/map/root") 
	  public String rootFomr(Model model, HttpSession session) { 
	  session.getAttribute("userId"); 
	  model.addAttribute("shop", shopService.findAllShop());
	  model.addAttribute("findAll", mapService.findAll()); 
	  return "map/root"; 
	  }
	  
	  
	  @PostMapping("/map/root")
	  public String root(Shop shop, HttpSession session) {
	  session.setAttribute("shopBns", shop.getBusinessNumber());
	  
	  return "redirect:/map/position";
	  }
	  
	  @GetMapping("/map/position") 
	  public String positionForm(Model model, HttpSession session) {
		  session.getAttribute("shopBns");
		  model.addAttribute("shop", shopService.findShopByBnsNum((String) session.getAttribute("shopBns")));
		  return "map/position";
	  }
	  
	  @PostMapping("/map/position") 
	  public String position(Map map, Model model,  HttpSession session) { 
	  Map map2 = new Map(); 
	  map2.setBusinessNumber((String) session.getAttribute("shopBns"));
	  map2.setLatitude(map.getLatitude());
	  map2.setLongitude(map.getLongitude());
	  mapService.insertPosition(map2);
	  return "redirect:/map/map"; 
	  }
	
	
	@GetMapping("/map/map")
	public String mapForm(Model model, HttpSession session) {
			// 모든 가게조회
			List<Shop> list = shopService.findAllShop();
			model.addAttribute("shopFind", list);
			
			//메뉴 출력
			List<String> bnsList = shopService.findAllBns();
			Set<String> set = new HashSet<String>(bnsList);
			List<String> newBnsList = new ArrayList<>(set);
			Collections.sort(newBnsList);
			List<List<Menu>> menuList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				menuList.add(shopService.findShopMenuByBnsNum(newBnsList.get(i)));
				System.out.println(i + ": " + menuList );
			
			//좌표 불러오기
			List<Map> map2 = mapService.findAll();
			
			System.out.println("List: " + menuList);
			model.addAttribute("find", map2);
			model.addAttribute("menuList", menuList);
		}

		// 아이디 세션

		if (session.getAttribute("userId") == null) {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "../login");
			return "alert/alert";
		}
		
		model.addAttribute("userId", session.getAttribute("userId"));

		/*
		 * 여기로 올때 temp 에 올렸던 이미지들을 자동으로 삭제한다. 2022-08-11 한태우(Shop 담당)
		 */

		// 가게 메뉴 삭제
		if (session.getAttribute("tempShopImg") != null) {
			for (String img : (List<String>) session.getAttribute("tempImgList")) {
				File menuImg = new File(
						"C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
				menuImg.delete();
			}
			session.removeAttribute("tempImgList");

			// 가게 이미지 삭제
			String img = (String) session.getAttribute("tempShopImg");
			File shopImg = new File(
					"C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
			shopImg.delete();
			session.removeAttribute("tempShopImg");
			// 문제 될시 주석 처리만 해주세용
		}		
		// bnsNum session delete
		session.removeAttribute("bnsNum");
		return "map/map";
	}


	@PostMapping("/map/map")
	public String map(Shop shop, Map map, Model model) {
		// 매장명으로 매장 정보 가져오기
		Shop shopName = new Shop();
		shopName = shopService.findAllbyShopNameObject(shop.getShopName());

		model.addAttribute("shop", shopName);
		model.addAttribute("menus", shopService.findShopMenuByBnsNum(shopName.getBusinessNumber()));

		return "shop/view/viewUserShop";
	}

	@PostMapping("/map/go_get_waiting")
	public String goGetWaiting() {
		return "redirect:/controller/get_waiting";
	}

}
