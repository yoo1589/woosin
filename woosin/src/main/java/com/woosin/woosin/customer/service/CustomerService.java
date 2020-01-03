package com.woosin.woosin.customer.service;

import java.util.List;
import java.util.Map;

import com.woosin.woosin.customer.vo.Timeline;

public interface CustomerService {
	// 연혁/타임라인 리스트
	public List<Timeline> getTimelineList();		
	// 기술자 리스트
	public Map<String, Object> getCoperration3(int currentPage, int rowPerPage);	
	// 관급 민간 공사 리스트
	public Map<String, Object> getCoperration(int currentPage, int rowPerPage);
	// 물류센터 공장 리스트
	public Map<String, Object> getCoperration2(int currentPage, int rowPerPage);
}
