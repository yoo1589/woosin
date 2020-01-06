package com.woosin.woosin.customer.service;

import java.util.List;
import java.util.Map;

import com.woosin.woosin.admin.vo.Franchisee;
import com.woosin.woosin.customer.vo.Community;
import com.woosin.woosin.customer.vo.LoginForm;
import com.woosin.woosin.customer.vo.Member;
import com.woosin.woosin.customer.vo.Timeline;

public interface CustomerService {
	// 썸네일 사진 조회
	public Map<String, Object> getFranchiseeThumbnail(List<Franchisee> franchiseeList);
	// 리스트 조회
	public List<Franchisee> getFranchiseeList();
	//로그인 정보
	public Member getMemberOne(LoginForm loginForm);
	// 공지사항 리스트
	public Map<String, Object> getCommunity3(int currentPage, int rowPerPage);		
	// 상담 리스트
	public Map<String, Object> getCommunity2(int currentPage, int rowPerPage);		
	// QnA 등록
	public int addCommunity(Community community);
	// 연혁/타임라인 리스트
	public List<Timeline> getTimelineList();		
	// 기술자 리스트
	public Map<String, Object> getCoperration3(int currentPage, int rowPerPage);	
	// 관급 민간 공사 리스트
	public Map<String, Object> getCoperration(int currentPage, int rowPerPage);
	// 물류센터 공장 리스트
	public Map<String, Object> getCoperration2(int currentPage, int rowPerPage);
}
