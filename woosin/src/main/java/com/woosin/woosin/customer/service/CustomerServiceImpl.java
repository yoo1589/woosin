package com.woosin.woosin.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woosin.woosin.customer.mapper.CustomerMapper;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosin2;
import com.woosin.woosin.customer.vo.CoperrationWoosin3;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage2;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage3;
import com.woosin.woosin.customer.vo.Timeline;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
@Autowired CustomerMapper customerMapper;
	// 타임라인/연혁 리스트 확인

	@Override
	public List<Timeline> getTimelineList() {
		System.out.println("타임라인리스트 서비스쪽");
		return customerMapper.selectTimelineList();
	}

	// 기술자 리스트확인
	@Override
	public Map<String, Object> getCoperration3(int currentPage, int rowPerPage) {
		
		// 페이징 코드
		// Mapper로 페이징 정보를 넘기기 위해 VO에 값 저장
		CoperrationWoosinPage3 coperrationWoosinPage3 = new CoperrationWoosinPage3();
		coperrationWoosinPage3.setRowPerPage(rowPerPage);
		coperrationWoosinPage3.setBeginRow((currentPage-1)*rowPerPage);
		
		List<CoperrationWoosin3> coperrationWoosinPageList = customerMapper.selectCoperrationWoosinPageList3(coperrationWoosinPage3);
	
		// 페이징 버튼을 위한 마지막 페이지 계산
		int totalRowCount = customerMapper.selectCoperrationWoosinPageCount3();
		int lastPage = 0;
		if(totalRowCount % rowPerPage == 0) {
			lastPage = totalRowCount / rowPerPage;
		} else {
			lastPage = totalRowCount / rowPerPage + 1;
		}
		System.out.println(coperrationWoosinPageList);
		// 페이징한 리스트와 현재 페이지 정보를 맵에 저장하여 리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", coperrationWoosinPageList);
		map.put("currentPage", currentPage);
		map.put("totalRowCount", totalRowCount);
		map.put("lastPage", lastPage);
		return map;
		}

	// 물류센터 리스트확인
	@Override
	public Map<String, Object> getCoperration2(int currentPage, int rowPerPage) {
		
		// 페이징 코드
		// Mapper로 페이징 정보를 넘기기 위해 VO에 값 저장
		CoperrationWoosinPage2 coperrationWoosinPage2 = new CoperrationWoosinPage2();
		coperrationWoosinPage2.setRowPerPage(rowPerPage);
		coperrationWoosinPage2.setBeginRow((currentPage-1)*rowPerPage);
		
		List<CoperrationWoosin2> coperrationWoosinPageList2 = customerMapper.selectCoperrationWoosinPageList2(coperrationWoosinPage2);
	
		// 페이징 버튼을 위한 마지막 페이지 계산
		int totalRowCount = customerMapper.selectCoperrationWoosinPageCount2();
		int lastPage = 0;
		if(totalRowCount % rowPerPage == 0) {
			lastPage = totalRowCount / rowPerPage;
		} else {
			lastPage = totalRowCount / rowPerPage + 1;
		}
		System.out.println(coperrationWoosinPageList2);
		// 페이징한 리스트와 현재 페이지 정보를 맵에 저장하여 리턴
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("list", coperrationWoosinPageList2);
		map2.put("currentPage", currentPage);
		map2.put("totalRowCount", totalRowCount);
		map2.put("lastPage", lastPage);
		return map2;
		}

	// 민간공사 리스트확인
	@Override
	public Map<String, Object> getCoperration(int currentPage, int rowPerPage) {
		
		// 페이징 코드
		// Mapper로 페이징 정보를 넘기기 위해 VO에 값 저장
		CoperrationWoosinPage coperrationWoosinPage = new CoperrationWoosinPage();
		coperrationWoosinPage.setRowPerPage(rowPerPage);
		coperrationWoosinPage.setBeginRow((currentPage-1)*rowPerPage);
		
		List<CoperrationWoosin> coperrationWoosinPageList = customerMapper.selectCoperrationWoosinPageList(coperrationWoosinPage);
	
		// 페이징 버튼을 위한 마지막 페이지 계산
		int totalRowCount = customerMapper.selectCoperrationWoosinPageCount();
		int lastPage = 0;
		if(totalRowCount % rowPerPage == 0) {
			lastPage = totalRowCount / rowPerPage;
		} else {
			lastPage = totalRowCount / rowPerPage + 1;
		}
		System.out.println(coperrationWoosinPageList);
		// 페이징한 리스트와 현재 페이지 정보를 맵에 저장하여 리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", coperrationWoosinPageList);
		map.put("currentPage", currentPage);
		map.put("totalRowCount", totalRowCount);
		map.put("lastPage", lastPage);
		return map;
		}

}
