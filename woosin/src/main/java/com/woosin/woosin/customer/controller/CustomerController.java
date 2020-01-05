package com.woosin.woosin.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.woosin.woosin.customer.service.CustomerService;
import com.woosin.woosin.customer.vo.Community;
import com.woosin.woosin.customer.vo.Timeline;

@Controller
public class CustomerController {
	@Autowired CustomerService customerService;
	// 공지사항 리스트
	@GetMapping("/Community3")
	public String Community3(Model model, 
								@RequestParam(value="currentPage", defaultValue="1") int currentPage
								){
		int rowPerPage = 10;
		Map<String, Object> map = customerService.getCommunity3(currentPage, rowPerPage);		
		model.addAttribute("map", map);
		System.out.println(map);
		return "Community3";
	}
	
	// 상담 리스트
	@GetMapping("/Community2")
	public String Community2(Model model, 
								@RequestParam(value="currentPage", defaultValue="1") int currentPage
								){
		int rowPerPage = 10;
		Map<String, Object> map = customerService.getCommunity2(currentPage, rowPerPage);		
		model.addAttribute("map", map);
		System.out.println(map);
		return "Community2";
	}
	
	// QnA 입력
	@PostMapping("/addCommunity")
    public String QnAFranchisee(Community community) {
		customerService.addCommunity(community);
		System.out.println("커뮤니티 컨트롤러"+community);
        return "redirect:/Community";
	}
	
	// 주요사업 내용
	@GetMapping("/Community")
    public String community(){
      return "Community";
	}
	
	// 타임라인/연혁 리스트
	@GetMapping("/woosinIntroduce4")
	public String coperrationWoosin4(Model model){
		List<Timeline> timeline = customerService.getTimelineList();
		System.out.println("timeline "+timeline);
		System.out.println("timeline 컨트롤러 호출");
		model.addAttribute("timeline", timeline);
		
		return "woosinIntroduce4";
	}	
	
	//customer index 요청
	@GetMapping({"/index","/"})
    public String customerIndex(){
      return "index";
	}
	//기본 템플릿확인용도
	@GetMapping("/templet")
    public String templet(){
      return "templet";
	}	
	// 주요사업 내용
	@GetMapping("/woosinCompuny")
    public String woosinCompuny(){
      return "woosinCompuny";
	}
	// 주요사업 내용2
	@GetMapping("/woosinCompuny2")
    public String woosinCompuny2(){
      return "woosinCompuny2";
	}
	// 소개
	@GetMapping("/woosinIntroduce")
    public String woosinIntroduce(){
      return "woosinIntroduce";
	}
	// 등록물정보
	@GetMapping("/woosinIntroduce2")
    public String woosinIntroduce2(){
      return "woosinIntroduce2";
	}
	// 오시는길
	@GetMapping("/woosinIntroduce3")
    public String woosinIntroduce3(){
      return "woosinIntroduce3";
	}
	@GetMapping("/registrationWoosin")
    public String registrationWoosin(){
      return "registrationWoosin";
	}	
	// 민간공사 리스트
	@GetMapping("/coperrationWoosin")
	public String coperrationWoosin(Model model, 
								@RequestParam(value="currentPage", defaultValue="1") int currentPage
								){
		int rowPerPage = 10;
		Map<String, Object> map = customerService.getCoperration(currentPage, rowPerPage);		
		model.addAttribute("map", map);
		System.out.println(map);
		return "coperrationWoosin";
	}
	// 민간공사 리스트
	@GetMapping("/coperrationWoosin2")
	public String coperrationWoosin2(Model model, 
								@RequestParam(value="currentPage", defaultValue="1") int currentPage
								){
		int rowPerPage = 10;
		Map<String, Object> map2 = customerService.getCoperration2(currentPage, rowPerPage);		
		model.addAttribute("map2", map2);
		System.out.println(map2);
		return "coperrationWoosin2";
	}
	
	/*
	 * // 기술자 리스트 보류
	 * 
	 * @GetMapping("/coperrationWoosin3") public String coperrationWoosin3(Model
	 * model,
	 * 
	 * @RequestParam(value="currentPage", defaultValue="1") int currentPage ){ int
	 * rowPerPage = 10; Map<String, Object> map =
	 * customerService.getCoperration3(currentPage, rowPerPage);
	 * model.addAttribute("map", map); System.out.println(map); return
	 * "coperrationWoosin3"; }
	 */
	@GetMapping("/coperrationWoosin3")
    public String coperrationWoosin3(){
      return "coperrationWoosin3";
	}
	
}
