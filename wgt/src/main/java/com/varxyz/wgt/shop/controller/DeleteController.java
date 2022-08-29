package com.varxyz.wgt.shop.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller
public class DeleteController {
	
	ShopService service = new ShopServiceImpl();
	
	@PostMapping("shop/deleteShopMenu")
	public String delMenu(Model model, 
			@RequestParam(required = false, value = "check") ArrayList<String> checkList) {
		
		if(checkList == null) {
			model.addAttribute("msg", "삭제할 메뉴를 체크 해주세요!");
			model.addAttribute("url","viewMyShop");
			return "alert/alert";
		}
			
		for (String name : checkList) {
			service.deleteShopMenu(name, service.findMenuByMenuName(name).getMenuImg());
		}
		
		model.addAttribute("msg","메뉴 삭제가 완료 되었습니다.");
		model.addAttribute("url","viewMyShop");
		return "alert/alert";
	}
}
