package com.woosin.woosin.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woosin.woosin.customer.mapper.CustomerMapper;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
@Autowired CustomerMapper customerMapper;

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
