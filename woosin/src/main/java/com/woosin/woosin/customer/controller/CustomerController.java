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
	@GetMapping("/index")
    public String customerIndex(){
      return "index";
	}
	@GetMapping("/templet")
    public String templet(){
      return "templet";
	}	
	@GetMapping("/woosinIntroduce")
    public String woosinIntroduce(){
      return "woosinIntroduce";
	}
	@GetMapping("/woosinIntroduce2")
    public String woosinIntroduce2(){
      return "woosinIntroduce2";
	}
	@GetMapping("/woosinIntroduce3")
    public String woosinIntroduce3(){
      return "woosinIntroduce3";
	}
	@GetMapping("/registrationWoosin")
    public String registrationWoosin(){
      return "registrationWoosin";
	}	
	
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
	
}
