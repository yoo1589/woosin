package com.woosin.woosin.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
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
	
}
