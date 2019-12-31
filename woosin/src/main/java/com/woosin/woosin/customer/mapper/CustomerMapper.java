package com.woosin.woosin.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosin2;
import com.woosin.woosin.customer.vo.CoperrationWoosin3;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage2;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage3;

@Mapper
public interface CustomerMapper {
	// 페이징 용도
	public int selectCoperrationWoosinPageCount3();
	public int selectCoperrationWoosinPageCount2();
	public int selectCoperrationWoosinPageCount();
	// 기술자 리스트
	public List<CoperrationWoosin3> selectCoperrationWoosinPageList3(CoperrationWoosinPage3 coperrationWoosinPage3);		
	// 물류및 공장 리스트
	public List<CoperrationWoosin2> selectCoperrationWoosinPageList2(CoperrationWoosinPage2 coperrationWoosinPage2);	
	// 관급및 민관공사 리스트
	public List<CoperrationWoosin> selectCoperrationWoosinPageList(CoperrationWoosinPage coperrationWoosinPage);
}
