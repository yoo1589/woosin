package com.woosin.woosin.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.woosin.woosin.customer.vo.CoperrationWoosin;
import com.woosin.woosin.customer.vo.CoperrationWoosinPage;

@Mapper
public interface CustomerMapper {
	public int selectCoperrationWoosinPageCount();
	public List<CoperrationWoosin> selectCoperrationWoosinPageList(CoperrationWoosinPage coperrationWoosinPage);
}
