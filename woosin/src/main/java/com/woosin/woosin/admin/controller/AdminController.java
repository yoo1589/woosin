package com.woosin.woosin.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.woosin.woosin.admin.service.AdminService;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.Timeline;

@Controller
public class AdminController {
	@Autowired AdminService adminService;
	
	
	// 연혁 리스트 삭제
	@GetMapping("/deleteCorperration3")
    public String cancelSeatReservation3(
    	@RequestParam(value="timelineNo") int timelineNo) {
		adminService.deleteCorperration3(timelineNo);
        return "redirect:/adminIndex";
	}	
	
	// 물류센터 삭제
	@GetMapping("/deleteCorperration2")
    public String cancelSeatReservation2(
    		@RequestParam(value="corperrationNo") int corperrationNo) {
		adminService.deleteCorperration2(corperrationNo);
        return "redirect:/adminIndex";
	}	
	
	// 민간공사 삭제
	@GetMapping("/deleteCorperration")
    public String cancelSeatReservation(
    		@RequestParam(value="corperrationNo") int corperrationNo) {
		adminService.deleteCorperration(corperrationNo);
        return "redirect:/adminIndex";
	}	
	
	// add 연혁
	@PostMapping("/addTimeline")
    public String addTimeline(Timeline timeline) {
		adminService.addTimeline(timeline);
        return "redirect:/adminIndex";
	}		
	// add 공장
	@PostMapping("/addCoperration2")
    public String addCoperration2(CoperrationWoosin coperrationWoosin) {
		System.out.println(coperrationWoosin);
		adminService.addCoperration2(coperrationWoosin);
        return "redirect:/adminIndex";
	}	
	// add 민간공사
	@PostMapping("/addCoperration1")
    public String addCoperration1(CoperrationWoosin coperrationWoosin) {
		System.out.println(coperrationWoosin);
		adminService.addCoperration1(coperrationWoosin);
        return "redirect:/adminIndex";
	}
	
	//Admin index 요청
	@GetMapping("/adminIndex")
    public String adminIndex(Model model, 
			@RequestParam(value="currentPage", defaultValue="1") int currentPage){
	//최대페이지	
	int rowPerPage = 10;
	// 물류공사 get요청
	Map<String, Object> map = adminService.getCoperration(currentPage, rowPerPage);		
	model.addAttribute("map", map);
	System.out.println("111111111111111"+map);	
	// 민간공사 get요청
	Map<String, Object> map1 = adminService.getCoperration2(currentPage, rowPerPage);		
	model.addAttribute("map1", map1);
	System.out.println("222222222222222"+map1);	
	
	List<Timeline> timeline = adminService.getTimelineList();
	System.out.println("timeline "+timeline);
	System.out.println("timeline 컨트롤러 호출");
	model.addAttribute("timeline", timeline);
	
    return "adminIndex";
	}
	
}
