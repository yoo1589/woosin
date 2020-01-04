package com.woosin.woosin.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosin2;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage2;
import com.woosin.woosin.customer.vo.Timeline;

@Mapper
public interface AdminMapper {
	// QnA 등록 
	public int insertCoperrationWoosin(CoperrationWoosin coperrationWoosin);
	// 타임라인/연혁 리스트
	public List<Timeline> selectTimelineList();
	// 페이징 용도
	public int selectCoperrationWoosinPageCount2();
	// 페이징 용도
	public int selectCoperrationWoosinPageCount();
	// 물류및 공장 리스트
	public List<CoperrationWoosin2> selectCoperrationWoosinPageList2(CoperrationWoosinPage2 coperrationWoosinPage2);	
	// 관급및 민관공사 리스트
	public List<CoperrationWoosin> selectCoperrationWoosinPageList(CoperrationWoosinPage coperrationWoosinPage);
}
