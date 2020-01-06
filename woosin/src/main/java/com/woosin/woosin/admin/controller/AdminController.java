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
import com.woosin.woosin.admin.vo.Franchisee;
import com.woosin.woosin.admin.vo.FranchiseeInfoForm;
import com.woosin.woosin.customer.vo.Community2;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.Timeline;

@Controller
public class AdminController {
	@Autowired AdminService adminService;
	// 가맹점 상품 삭제 요청
	
	
	@GetMapping("/removeFranchisee")
	public String modifyFranchiseeInfo(FranchiseeInfoForm franchiseeInfoForm) {
		System.out.println("modifyFranchiseeInfo POST 요청");
		System.out.println("Controller franchiseeInfoForm: " + franchiseeInfoForm);
		
		int rows = adminService.modifyFranchiseeInfo(franchiseeInfoForm);
		if(rows < 0) 
			System.out.println("파일 업로드 안 됨");
		else
			System.out.println("처리된 행의 수: " + rows);
		
		return "redirect:/detailAdmin?franchiseeNo="+franchiseeInfoForm.getFranchiseeNo();
	}
	
	// 게시글 정보 디테일
	@GetMapping("/detailFranchisee")
	public String detailFranchisee(Model model, @RequestParam("franchiseeNo")int franchiseeNo) {
		System.out.println("컨트롤러요청");
		System.out.println(franchiseeNo);
		List<Franchisee> list = adminService.getFranchiseeList(franchiseeNo);
		model.addAttribute("list",list);
		// 가맹점 정보 사진, 업로드 경로, pc사양 가져와서 model로 넘김
		Map<String, Object> franchiseeInfo = adminService.getFranchiseeInfo(franchiseeNo);
		System.out.println("franchiseeInfo:" + franchiseeInfo);
		model.addAttribute("franchiseeInfo", franchiseeInfo);
		
		return "detailFranchisee";
	}
	
	// 게시글 정보 디테일
	@GetMapping("/detailAdmin")
	public String QnaCustomerDetail(HttpSession session, Model model, @RequestParam("franchiseeNo")int franchiseeNo) {
		if (session.getAttribute("id") == null) {
			return "redirect:/";
		}	
		System.out.println("컨트롤러요청");
		System.out.println(franchiseeNo);
		List<Franchisee> list = adminService.getFranchiseeList(franchiseeNo);
		model.addAttribute("list",list);
		// 가맹점 정보 사진, 업로드 경로, pc사양 가져와서 model로 넘김
		Map<String, Object> franchiseeInfo = adminService.getFranchiseeInfo(franchiseeNo);
		System.out.println("franchiseeInfo:" + franchiseeInfo);
		model.addAttribute("franchiseeInfo", franchiseeInfo);
		
		return "detailAdmin";
	}
	
	// 사진 정보 입력
	@PostMapping("/addPic")
	public String addPic(FranchiseeInfoForm franchiseeInfoForm) {
		System.out.println("addFranchiseeInfo POST 요청");
		
		System.out.println("Controller franchiseeInfoForm: " + franchiseeInfoForm);
		
		int rows = adminService.addFranchiseeInfo(franchiseeInfoForm);
		
		if(rows < 0) 
			System.out.println("파일 형식 오류");
		else
			System.out.println("success rows : "+ rows);
		
		return "redirect:/detailAdmin?franchiseeNo="+franchiseeInfoForm.getFranchiseeNo();
	}
	
	// 공지사항 리스트 삭제
	@GetMapping("/deleteCommunity2")
    public String deleteCommunity2(
    	@RequestParam(value="communityNo") int communityNo) {
		adminService.deleteCummnity2(communityNo);
        return "redirect:/adminIndex";
	}	
	
	// 문의 리스트 삭제
	@GetMapping("/deleteCummnity")
    public String deleteCummnity(
    	@RequestParam(value="communityNo") int communityNo) {
		adminService.deleteCummnity(communityNo);
        return "redirect:/adminIndex";
	}	
	
	// 연혁 리스트 삭제
	@GetMapping("/deleteCorperration3")
    public String cancelSeatReservation3(
    	@RequestParam(value="timelineNo") int timelineNo) {
		adminService.deleteCorperration3(timelineNo);
        return "redirect:/adminIndex";
	}	
	
	// 물류센터 삭제
	@GetMapping("/delFranchisee")
    public String delFranchisee(
    		@RequestParam(value="franchiseeNo") int franchiseeNo) {
		adminService.delFranchisee(franchiseeNo);
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
	// add 게시글
	@PostMapping("/addTitle")
    public String addTitle(Franchisee franchisee) {
		adminService.addTitle(franchisee);
        return "redirect:/adminIndex";
	}	
	
	// add 공지사항
	@PostMapping("/addCommunity2")
    public String addCommunity2(Community2 community2) {
		adminService.addCommunity2(community2);
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
    public String adminIndex(Model model, HttpSession session,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage){
		
	if (session.getAttribute("id") == null) {
	return "redirect:/";
	}	
	
	//최대페이지	
	int rowPerPage = 10;
	// 물류공사 get요청
	Map<String, Object> map = adminService.getCoperration(currentPage, rowPerPage);		
	model.addAttribute("map", map);
	//System.out.println("111111111111111"+map);
	
	// 민간공사 get요청
	Map<String, Object> map1 = adminService.getCoperration2(currentPage, rowPerPage);		
	model.addAttribute("map1", map1);
	//System.out.println("222222222222222"+map1);	
	
	// 고객문의 get요청
	Map<String, Object> map3 = adminService.getCummunity(currentPage, rowPerPage);		
	model.addAttribute("map3", map3);
	//System.out.println("3333333333333333"+map3);
	
	// 게시글 get요청
	Map<String, Object> map5 = adminService.getTitle(currentPage, rowPerPage);		
	model.addAttribute("map5", map5);
	System.out.println("5555555555555555"+map5);	
	
	// 연혁리스트 페이징 x
	List<Timeline> timeline = adminService.getTimelineList();
	//System.out.println("timeline "+timeline);
	//System.out.println("timeline 컨트롤러 호출");
	model.addAttribute("timeline", timeline);
	// 공지사항 리스트 페이징 x
	List<Community2> community2 = adminService.getCommunity2List();
	model.addAttribute("community2", community2);
    return "adminIndex";
	}
	
}
