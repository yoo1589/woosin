package com.woosin.woosin.customer.service;

import java.util.Map;

public interface CustomerService {
	// 기술자 리스트
	public Map<String, Object> getCoperration3(int currentPage, int rowPerPage);	
	// 관급 민간 공사 리스트
	public Map<String, Object> getCoperration(int currentPage, int rowPerPage);
	// 물류센터 공장 리스트
	public Map<String, Object> getCoperration2(int currentPage, int rowPerPage);
}
