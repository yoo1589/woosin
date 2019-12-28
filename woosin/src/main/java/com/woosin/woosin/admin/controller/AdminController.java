package com.woosin.woosin.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	//Admin index 요청
	@GetMapping("/adminIndex")
    public String adminIndex(HttpSession session){
		if (session.getAttribute("memberNo") == null) {
         return "redirect:/";
		}
      return "adminIndex";
	}
	
}
