package com.woosin.woosin.customer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.woosin.woosin.customer.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired CustomerService customerService;
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
	
	// 기술자 리스트
	@GetMapping("/coperrationWoosin3")
	public String coperrationWoosin3(Model model, 
								@RequestParam(value="currentPage", defaultValue="1") int currentPage
								){
		int rowPerPage = 10;
		Map<String, Object> map = customerService.getCoperration3(currentPage, rowPerPage);		
		model.addAttribute("map", map);
		System.out.println(map);
		return "coperrationWoosin3";
	}
	
}
