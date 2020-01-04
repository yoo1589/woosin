package com.woosin.woosin.admin.service;

import java.util.List;
import java.util.Map;

import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.Timeline;

public interface AdminService {
	// 연혁 리스트 삭제
	public int deleteCorperration3(int timelineNo);		
	// 물류센터 삭제
	public int deleteCorperration2(int corperrationNo);	
	// 민간공사 삭제
	public int deleteCorperration(int corperrationNo);
	// 타임라인/연혁 추가
	public int addTimeline(Timeline timeline);
	// 공장,물류센터 추가
	public int addCoperration2(CoperrationWoosin coperrationWoosin);		
	// 민간공사 추가
	public int addCoperration1(CoperrationWoosin coperrationWoosin);	
	// 연혁/타임라인 리스트
	public List<Timeline> getTimelineList();
	// 관급 민간 공사 리스트
	public Map<String, Object> getCoperration(int currentPage, int rowPerPage);
	// 물류센터 공장 리스트
	public Map<String, Object> getCoperration2(int currentPage, int rowPerPage);
}
