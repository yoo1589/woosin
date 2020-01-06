package com.woosin.woosin.admin.service;

import java.util.List;
import java.util.Map;

import com.woosin.woosin.admin.vo.Franchisee;
import com.woosin.woosin.admin.vo.FranchiseeInfoForm;
import com.woosin.woosin.customer.vo.Community2;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.Timeline;

public interface AdminService {
	// 게시 정보 삭제
	public int modifyFranchiseeInfo(FranchiseeInfoForm franchiseeInfoForm);
	// 게시글 사진리스트 조회
	public Map<String, Object> getFranchiseeInfo(int franchiseeNo);
	// 가맹점 정보 입력
	public int addFranchiseeInfo(FranchiseeInfoForm FranchiseeInfoForm);
	// 게시글 삭제
	public int delFranchisee(int franchiseeNo);	
	// 공지사항 리스트 삭제
	public int deleteCummnity2(int communityNo);		
	// 문의 리스트 삭제
	public int deleteCummnity(int communityNo);		
	// 연혁 리스트 삭제
	public int deleteCorperration3(int timelineNo);		
	// 물류센터 삭제
	public int deleteCorperration2(int corperrationNo);	
	// 민간공사 삭제
	public int deleteCorperration(int corperrationNo);
	// 게시글 추가
	public int addTitle(Franchisee franchisee);	
	// 공지사항 추가
	public int addCommunity2(Community2 community2);	
	// 타임라인/연혁 추가
	public int addTimeline(Timeline timeline);
	// 공장,물류센터 추가
	public int addCoperration2(CoperrationWoosin coperrationWoosin);		
	// 민간공사 추가
	public int addCoperration1(CoperrationWoosin coperrationWoosin);
	// 공지사항 리스트
	public Map<String, Object> getTitle(int currentPage, int rowPerPage);
	// 게시글 리스트
	public List<Franchisee> getFranchiseeList(int franchiseeNo);	
	// 공지사항 리스트
	public List<Community2> getCommunity2List();	
	// 연혁/타임라인 리스트
	public List<Timeline> getTimelineList();
	// 문의내용 리스트
	public Map<String, Object> getCummunity(int currentPage, int rowPerPage);
	// 관급 민간 공사 리스트
	public Map<String, Object> getCoperration(int currentPage, int rowPerPage);
	// 물류센터 공장 리스트
	public Map<String, Object> getCoperration2(int currentPage, int rowPerPage);
}
