package com.varxyz.wgt.waiting.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;
import com.varxyz.wgt.waiting.domain.Waiting;
import com.varxyz.wgt.waiting.service.WaitingService;
import com.varxyz.wgt.waiting.serviceImpl.WaitingServiceImpl;

@Controller("controller.waitingController")
public class WaitingController {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	WaitingService waitingService = new WaitingServiceImpl();
	ShopService shopService = new ShopServiceImpl();

	@GetMapping("/controller/waiting")
	public String waitingForm(Model model, HttpSession session) {
		List<Waiting> nowWaiting = waitingService.findAllWaiting((String)session.getAttribute("shopName"));
		long count = 0;
		if(nowWaiting.get(0).getUserId() == "없음") {
			count = 0;
		}else {
			for (int i = 0; i < nowWaiting.size(); i++) {
				count++;
			}
		}
		model.addAttribute("barName", session.getAttribute("shopName")); 
		model.addAttribute("nowWaitingCount", count);
		return "waiting/add_waiting";
	}

	@PostMapping("/controller/waiting")
	public String waiting(Waiting waiting, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		if (waitingService.findWaitingById(userId).get(0).getBarName() != "없음") {
			model.addAttribute("msg", "웨이팅은 한 매장만 가능합니다.");
			return "error/waitingError";
		}
		waitingService.addWaiting((String)session.getAttribute("shopName"), userId, waiting.getNum_people());
		return "redirect:/controller/get_waiting";
	}

	@GetMapping("/controller/get_waiting")
	public String getWaitingForm(Model model, HttpSession session) throws ParseException {
		String userId = (String) session.getAttribute("userId");
		// 웨이팅을 하지 않았을 때
		if (waitingService.findWaitingById(userId).get(0).getBarName() == "없음") {
			List<Waiting> noWaiting = waitingService.findWaitingById(userId);
			model.addAttribute("frontCount", "0");
			model.addAttribute("allCount", "0");
			model.addAttribute("waiting", noWaiting);
			model.addAttribute("shopTel", "-");
			return "waiting/get_waiting";
		}

		// 웨이팅 해둔 상태 일때
		List<Waiting> waitingList = waitingService.findAllWaiting(
				waitingService.findWaitingById(userId).get(0).getBarName());
		long allCount = 0;
		long frontCount = 0;

		try {
			Date day1;
			Date day2;
			day2 = format
					.parse(waitingService.findWaitingById(userId).get(0).getRegDate());
			for (int i = 0; i < waitingList.size(); i++) {
				allCount++; // 특정 매장에대한 나 포함 모든 웨이팅 수
				day1 = format.parse(waitingList.get(i).getRegDate());
				int compare = day1.compareTo(day2);
				if (compare < 0) {
					frontCount++; // 내 앞의 웨이팅 수
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addAttribute("frontCount", frontCount);
		model.addAttribute("allCount", allCount);
		model.addAttribute("waiting", waitingService.findWaitingById(userId));
		model.addAttribute("shopTel",
				shopService.findAllByShopName(
						waitingService.findWaitingById(userId).get(0).getBarName()).get(0).getShopTel());
		
		// 내 앞 대기팀이 0팀 일때
		if (frontCount == 0) {
			// 언제까지오라는 시간 부여받지 않았을때 or waitingStartTime이 0 일때
			if (waitingService.findWaitingById(userId).get(0).getWaitingStartTime()
					.equals("0")) {
				SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
				Date nowDate = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(nowDate);
				cal.add(Calendar.MINUTE, 1); // 웨이팅 타이머 1 -> 1분
				String outputText = outputFormat.format(cal.getTime());

				waitingService.addWaitingTime(userId, outputText);
				String waitingTime = waitingService.findWaitingById(userId).get(0)
						.getWaitingStartTime();
				model.addAttribute("msg", waitingTime + " 까지 와주시기 바랍니다. (자동취소 예정)");
				return "waiting/get_waiting";
			} else {
				// 언제까지 오라는 시간을 부여 받은 상황
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				Date nowDate = new Date();
				Calendar cal = Calendar.getInstance();
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				String waitingTime = waitingService.findWaitingById(userId).get(0)
						.getWaitingStartTime();
				
				cal.setTime(formatter.parse(waitingTime));
				int yearDate = Integer.parseInt(df.format(nowDate.getTime()).split("-", 0)[0]);
				int monthDate = Integer.parseInt(df.format(nowDate.getTime()).split("-", 0)[1]);
				int dateDate = Integer.parseInt(df.format(nowDate.getTime()).split("-", 0)[2]);
				
//				System.out.println("현재시간 : " + nowDate);
//				System.out.println("비교시간 : " + formatter.parse(waitingTime));
				cal.set(yearDate, monthDate-1, dateDate);
//				System.out.println("만든시간 : " + cal.getTime());
				
				model.addAttribute("msg", waitingTime + " 까지 와주시기 바랍니다. (자동취소 예정)");
//				System.out.println("비교결과 : " + nowDate.after(cal.getTime()));
				if ( nowDate.after(cal.getTime()) ) { 
					waitingService.deleteWaiting(userId);
					return "redirect:/controller/get_waiting";
				}
			}
		}
		
		return "waiting/get_waiting";
	}

	@PostMapping("/controller/get_waiting")
	public String getWaiting(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		waitingService.deleteWaiting(userId);
		return "redirect:/controller/get_waiting";
	}

	@GetMapping("/controller/waiting_management")
	public String waitingManagement(HttpSession session, Model model) {
		List<Waiting> MyShopWaitingList = waitingService.findAllWaiting((String)session.getAttribute("shopNameForManager"));

		model.addAttribute("MyShopWaitingList", MyShopWaitingList);
		model.addAttribute("isUser",MyShopWaitingList.get(0).getUserId());
		return "waiting/waiting_management";
	}

	@PostMapping("/controller/waitingCheck")
	public String waitingCheck(HttpServletRequest request) {
		waitingService.deleteWaiting(request.getParameter("userId"));
		return "redirect:/controller/waiting_management";
	}

	@PostMapping("/controller/allWaitingClear")
	public String allWaitingClear(HttpSession session) {
		waitingService.deteleAllWaiting((String)session.getAttribute("shopNameForManager"));
		return "redirect:/shop/viewMyShop";
	}

}
