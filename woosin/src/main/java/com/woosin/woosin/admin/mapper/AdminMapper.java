package com.woosin.woosin.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.woosin.woosin.admin.vo.Delete;
import com.woosin.woosin.admin.vo.Franchisee;
import com.woosin.woosin.admin.vo.FranchiseePic;
import com.woosin.woosin.admin.vo.FranchiseeSpec;
import com.woosin.woosin.customer.vo.Community;
import com.woosin.woosin.customer.vo.Community2;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosin2;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage2;
import com.woosin.woosin.customer.vo.Timeline;

@Mapper
public interface AdminMapper {
	public List<String> selectDeleteList(int franchiseeNo);	
	// 사진 번호로 가맹점 사진 하나 조회
	public FranchiseePic selectFranchiseePicOne(int picNo);
	// 게시글  삭제
	public int removeFranchisee2(int franchiseeNo);	
	// 게시글 내용 삭제
	public int deletefranchiseeSpec(int franchiseeNo1);
	// 사진 삭제
	public int deletefranchiseePic(int franchiseeNo1);
	// 게시글 삭제
	public int deletefranchisee(int franchiseeNo);
	// 사진 조회
	public List<FranchiseePic> selectFranchiseePic(int franchiseeNo);
	// 내용 조회
	public FranchiseeSpec selelctFranchiseeSpec(int franchiseeNo);
	// 사진 정보 입력
	public int insertFranchiseeSpec(FranchiseeSpec franchiseeSpec);
	// 사진 입력
	public int insertFranchiseePic(FranchiseePic franchiseePic);
	// 페이징 용도
	public int selectCummunityCount();
	// 문의내용 리스트
	public List<Community> selectCummunityList(CoperrationWoosinPage2 coperrationWoosinPage2);
	// 공지사항 리스트 삭제
	public int removeCummnity2(int communityNo);		
	// 문의 리스트 삭제
	public int removeCummnity(int communityNo);		
	// 연혁 리스트 삭제
	public int removeCorperration3(int timelineNo);		
	// 공장 리스트 삭제
	public int removeCorperration2(int corperrationNo);		
	// 민간 공사 삭제
	public int removeCorperration(int corperrationNo);
	// 게시글 추가
	public int insertTitle(Franchisee franchisee);	
	// 공지사항 추가
	public int insertCommunity2(Community2 community2);	
	// 타임라인 연혁 
	public int insertTimeline(Timeline timeline);	
	// 물류센터및 공장 등록 
	public int insertCoperrationWoosin2(CoperrationWoosin coperrationWoosin);	
	// 민간공사 등록 
	public int insertCoperrationWoosin(CoperrationWoosin coperrationWoosin);
	// 게시글 리스트
	public List<Franchisee> selectTitle(CoperrationWoosinPage2 coperrationWoosinPage2);	
	// 게시글 상세보기
	public List<Franchisee> selectFranchiseeList(int franchiseeNo);	
	// 공지사항 리스트
	public List<Community2> selectCommunity2List();		
	// 타임라인/연혁 리스트
	public List<Timeline> selectTimelineList();
	// 페이징 용도
	public int selectTitleCount();	
	// 페이징 용도
	public int selectCoperrationWoosinPageCount2();
	// 페이징 용도
	public int selectCoperrationWoosinPageCount();
	// 물류및 공장 리스트
	public List<CoperrationWoosin2> selectCoperrationWoosinPageList2(CoperrationWoosinPage2 coperrationWoosinPage2);	
	// 관급및 민관공사 리스트
	public List<CoperrationWoosin> selectCoperrationWoosinPageList(CoperrationWoosinPage coperrationWoosinPage);
}
