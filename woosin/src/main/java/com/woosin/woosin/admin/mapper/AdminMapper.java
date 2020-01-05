package com.woosin.woosin.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.woosin.woosin.customer.vo.Community;
import com.woosin.woosin.customer.vo.Community2;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosin2;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage2;
import com.woosin.woosin.customer.vo.Timeline;

@Mapper
public interface AdminMapper {
	// 페이징 용도
	public int selectCummunityCount();
	// 문의내용 리스트
	public List<Community> selectCummunityList(CoperrationWoosinPage2 coperrationWoosinPage2);	
	// 문의 리스트 삭제
	public int removeCummnity(int communityNo);		
	// 연혁 리스트 삭제
	public int removeCorperration3(int timelineNo);		
	// 공장 리스트 삭제
	public int removeCorperration2(int corperrationNo);		
	// 민간 공사 삭제
	public int removeCorperration(int corperrationNo);	
	// 타임라인 연혁 
	public int insertTimeline(Timeline timeline);	
	// 물류센터및 공장 등록 
	public int insertCoperrationWoosin2(CoperrationWoosin coperrationWoosin);	
	// 민간공사 등록 
	public int insertCoperrationWoosin(CoperrationWoosin coperrationWoosin);
	// 공지사항 리스트
	public List<Community2> selectCommunity2List();		
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
