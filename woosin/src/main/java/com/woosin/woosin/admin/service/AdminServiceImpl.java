package com.woosin.woosin.admin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.woosin.woosin.admin.mapper.AdminMapper;
import com.woosin.woosin.admin.vo.Franchisee;
import com.woosin.woosin.admin.vo.FranchiseeInfoForm;
import com.woosin.woosin.admin.vo.FranchiseePic;
import com.woosin.woosin.admin.vo.FranchiseeSpec;
import com.woosin.woosin.customer.vo.Community;
import com.woosin.woosin.customer.vo.Community2;
import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosin2;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage2;
import com.woosin.woosin.customer.vo.Timeline;
import com.woosin.woosin.ftp.FTPService;


@Service
@Transactional
public class AdminServiceImpl implements AdminService{		
@Autowired AdminMapper adminMapper;
	// 가맹점 정보 입력
	@Override
	public int addFranchiseeInfo(FranchiseeInfoForm franchiseeInfoForm) {
		// 성공한 처리수를 리턴할 변수
		int rows = 0;
		
		/*
		 *  업로드 가능한 이미지 타입 
		 
			MIME 타입	이미지 타입
			image/gif	GIF 이미지 (무손실 압축, PNG에 의해 대체됨)
			image/jpeg	JPEG 이미지
			image/png	PNG 이미지
			image/svg+xml	SVG 이미지 (벡터 이미지)
			
		 */
		
		// FranchiseeInfoForm 에서 -> franchiseePic, franchiseeSpec 으로 분리하여 처리
		
		// 1. FranchiseeSpec
		
		// FranchiseeInfo로 db에 저장
		FranchiseeSpec franchiseeSpec = new FranchiseeSpec();
		franchiseeSpec.setPicContent(franchiseeInfoForm.getPicContent());
		
		System.out.println("getContent: " + franchiseeInfoForm.getPicContent());

		rows += adminMapper.insertFranchiseeSpec(franchiseeSpec);
		
		// 2. FranchiseePic
		
		// 파일 리스트 가져옴
		List<MultipartFile> picList = franchiseeInfoForm.getFranchiseePicList();
		// 파일로 저장할 사진 리스트
		List<FranchiseePic> fileList = new ArrayList<FranchiseePic>();
		
		// 사진 리스트에서 하나씩 정보를 추출하여 db에 저장
		for(MultipartFile mf : picList) {
			String contentType = mf.getContentType();
			String name = mf.getName();
			String originName = mf.getOriginalFilename();
			long size = mf.getSize();
			// 파일 확장자명
			String extension = originName.substring(originName.lastIndexOf(".")+1);
			// 랜덤한 UUID에 -를 빼고 원래 파일이름의 확장자만 더해서 저장할 파일이름을 생성
			String saveFileName = UUID.randomUUID().toString().replace("-", "")+"."+extension;
						
			System.out.println("addFranchiseeInfo contentType: " + contentType);
			System.out.println("addFranchiseeInfo name: " + name);
			System.out.println("addFranchiseeInfo originName: " + originName);
			System.out.println("addFranchiseeInfo size: " + size);
			System.out.println("addFranchiseeInfo extension: " + extension);
			System.out.println("addFranchiseeInfo saveFileName: " + saveFileName);
			
			if(!contentType.equals("image/jpeg") && !contentType.equals("image/png") && 
					!contentType.equals("image/gif") && !contentType.equals("image/svg+xml") ) {
				return -1;
			}
			
			// franchiseePic으로  db에 저장
			FranchiseePic franchiseePic = new FranchiseePic();
			franchiseePic.setContentType(contentType);
			franchiseePic.setExtension(extension);
			franchiseePic.setFileName(saveFileName);
			franchiseePic.setOriginName(originName);
			franchiseePic.setSize(size);
			// 파일로 저장할 사진 목록에 추가
			fileList.add(franchiseePic);
			rows += adminMapper.insertFranchiseePic(franchiseePic);
		}
		
		// 파일로 저장할 목록 인덱스
		int FileListIndex = 0;
		
		// CDN 서버에 파일 저장
		// SQL예외가 발생하면 파일이 저장되지 않아야 하므로 맨 마지막에 실행
		for(MultipartFile mf : picList) {
			
			// saveFileName을 가져오기 위한 객체
			FranchiseePic franchiseePic = fileList.get(FileListIndex);
			
			// 저장될 파일 이름
			String storeFileName = franchiseePic.getFileName();
			// 업로드 디렉토리
			String dir = "/www/franchisee/";
			// 업로드 결과
			boolean result = false;
			try {
				// MultipartFile을 File로 변환
				String fileName = mf.getOriginalFilename();
				File convertFile = new File(fileName);
				convertFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(convertFile);
				fos.write(mf.getBytes());
				fos.close();
				
				// CDN에 업로드 시작
				System.out.println("Upload Start");
				FTPService ftpUploader = new FTPService();
				// FTP 연결
				ftpUploader.connectFTP(dir);
				// 파일 업로드
				result = ftpUploader.uploadFile(convertFile, storeFileName);
				// FTP 연결 해제
				ftpUploader.disconnect();
				System.out.println("Done");
				
			} catch (Exception e) {
				e.printStackTrace();
				// 파일을 저장할때 예외가 나면 rollback 시키기 위해서 강제로 런타임 예외 발생시킴.
				throw new RuntimeException();
			}
			if(result) {
				System.out.println("업로드 성공");
			} else {
				System.out.println("업로드 실패");
			}
			// 인덱스 증가
			FileListIndex++;
		}
		
		return rows;
	}

	// 게시판 리스트확인
	@Override
	public Map<String, Object> getTitle(int currentPage, int rowPerPage) {
	
		// 페이징 코드
		// Mapper로 페이징 정보를 넘기기 위해 VO에 값 저장
		CoperrationWoosinPage2 coperrationWoosinPage2 = new CoperrationWoosinPage2();
		coperrationWoosinPage2.setRowPerPage(rowPerPage);
		coperrationWoosinPage2.setBeginRow((currentPage-1)*rowPerPage);
		
		List<Franchisee> titlePageList = adminMapper.selectTitle(coperrationWoosinPage2);
	
		// 페이징 버튼을 위한 마지막 페이지 계산
		int totalRowCount = adminMapper.selectTitleCount();
		int lastPage = 0;
		if(totalRowCount % rowPerPage == 0) {
			lastPage = totalRowCount / rowPerPage;
		} else {
			lastPage = totalRowCount / rowPerPage + 1;
		}
		System.out.println(titlePageList);
		// 페이징한 리스트와 현재 페이지 정보를 맵에 저장하여 리턴
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("list", titlePageList);
		map5.put("currentPage", currentPage);
		map5.put("totalRowCount", totalRowCount);
		map5.put("lastPage", lastPage);
		return map5;
		}
	
	// 공지사항 리스트확인
	@Override
	public Map<String, Object> getCummunity(int currentPage, int rowPerPage) {
	
		// 페이징 코드
		// Mapper로 페이징 정보를 넘기기 위해 VO에 값 저장
		CoperrationWoosinPage2 coperrationWoosinPage2 = new CoperrationWoosinPage2();
		coperrationWoosinPage2.setRowPerPage(rowPerPage);
		coperrationWoosinPage2.setBeginRow((currentPage-1)*rowPerPage);
		
		List<Community> cummunityCountPageList = adminMapper.selectCummunityList(coperrationWoosinPage2);
	
		// 페이징 버튼을 위한 마지막 페이지 계산
		int totalRowCount = adminMapper.selectCummunityCount();
		int lastPage = 0;
		if(totalRowCount % rowPerPage == 0) {
			lastPage = totalRowCount / rowPerPage;
		} else {
			lastPage = totalRowCount / rowPerPage + 1;
		}
		System.out.println(cummunityCountPageList);
		// 페이징한 리스트와 현재 페이지 정보를 맵에 저장하여 리턴
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("list", cummunityCountPageList);
		map3.put("currentPage", currentPage);
		map3.put("totalRowCount", totalRowCount);
		map3.put("lastPage", lastPage);
		return map3;
		}
	// 공지사항 리스트 삭제
	@Override
	public int deleteCummnity2(int communityNo) {		
		return adminMapper.removeCummnity2(communityNo);
	}
	// 문의 리스트 삭제
	@Override
	public int deleteCummnity(int communityNo) {		
		return adminMapper.removeCummnity(communityNo);
	}
	// 연혁 리스트 삭제
	@Override
	public int deleteCorperration3(int timelineNo) {		
		return adminMapper.removeCorperration3(timelineNo);
	}
	// 공장 리스트 삭제
	@Override
	public int deleteCorperration2(int corperrationNo) {		
		return adminMapper.removeCorperration2(corperrationNo);
	}
	// 민강공사 리스트 삭제
	@Override
	public int deleteCorperration(int corperrationNo) {		
		return adminMapper.removeCorperration(corperrationNo);
	}
	// 게시글 리스트 추가
	@Override
	public int addTitle(Franchisee franchisee) {		
		return adminMapper.insertTitle(franchisee);
	}		
	// 공지사항 리스트 추가
	@Override
	public int addCommunity2(Community2 community2) {		
		return adminMapper.insertCommunity2(community2);
	}	
	// 연혁 리스트 추가
	@Override
	public int addTimeline(Timeline timeline) {		
		return adminMapper.insertTimeline(timeline);
	}
	// 공장 리스트 추가
	@Override
	public int addCoperration2(CoperrationWoosin coperrationWoosin) {		
		return adminMapper.insertCoperrationWoosin2(coperrationWoosin);
	}
	// 민강공사 리스트 추가
	@Override
	public int addCoperration1(CoperrationWoosin coperrationWoosin) {		
		return adminMapper.insertCoperrationWoosin(coperrationWoosin);
	}
	
	
	// 공지사항 리스트 확인
	@Override
	public List<Community2> getCommunity2List() {
		return adminMapper.selectCommunity2List();
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
