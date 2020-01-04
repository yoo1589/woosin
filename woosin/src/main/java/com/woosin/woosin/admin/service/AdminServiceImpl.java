package com.woosin.woosin.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woosin.woosin.admin.mapper.AdminMapper;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosin2;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage2;
import com.woosin.woosin.customer.vo.Timeline;


@Service
@Transactional
public class AdminServiceImpl implements AdminService{
@Autowired AdminMapper adminMapper;

	@Override
	public int addCoperration1(CoperrationWoosin coperrationWoosin) {		
		return adminMapper.insertCoperrationWoosin(coperrationWoosin);
	}

	// 타임라인/연혁 리스트 확인
	@Override
	public List<Timeline> getTimelineList() {
		//System.out.println("타임라인리스트 서비스쪽");
		return adminMapper.selectTimelineList();
	}
	
	// 물류센터 리스트확인
	@Override
	public Map<String, Object> getCoperration2(int currentPage, int rowPerPage) {
	
		// 페이징 코드
		// Mapper로 페이징 정보를 넘기기 위해 VO에 값 저장
		CoperrationWoosinPage2 coperrationWoosinPage2 = new CoperrationWoosinPage2();
		coperrationWoosinPage2.setRowPerPage(rowPerPage);
		coperrationWoosinPage2.setBeginRow((currentPage-1)*rowPerPage);
		
		List<CoperrationWoosin2> coperrationWoosinPageList2 = adminMapper.selectCoperrationWoosinPageList2(coperrationWoosinPage2);
	
		// 페이징 버튼을 위한 마지막 페이지 계산
		int totalRowCount = adminMapper.selectCoperrationWoosinPageCount2();
		int lastPage = 0;
		if(totalRowCount % rowPerPage == 0) {
			lastPage = totalRowCount / rowPerPage;
		} else {
			lastPage = totalRowCount / rowPerPage + 1;
		}
		System.out.println(coperrationWoosinPageList2);
		// 페이징한 리스트와 현재 페이지 정보를 맵에 저장하여 리턴
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("list", coperrationWoosinPageList2);
		map1.put("currentPage", currentPage);
		map1.put("totalRowCount", totalRowCount);
		map1.put("lastPage", lastPage);
		return map1;
		}
	
	// 민간공사 리스트확인
	@Override
	public Map<String, Object> getCoperration(int currentPage, int rowPerPage) {
		
		// 페이징 코드
		// Mapper로 페이징 정보를 넘기기 위해 VO에 값 저장
		CoperrationWoosinPage coperrationWoosinPage = new CoperrationWoosinPage();
		coperrationWoosinPage.setRowPerPage(rowPerPage);
		coperrationWoosinPage.setBeginRow((currentPage-1)*rowPerPage);
		
		List<CoperrationWoosin> coperrationWoosinPageList = adminMapper.selectCoperrationWoosinPageList(coperrationWoosinPage);
	
		// 페이징 버튼을 위한 마지막 페이지 계산
		int totalRowCount = adminMapper.selectCoperrationWoosinPageCount();
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
