package com.woosin.woosin.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woosin.woosin.admin.mapper.AdminMapper;


@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
@Autowired AdminMapper adminMapper;

}
