package com.woosin.woosin.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woosin.woosin.customer.mapper.CustomerMapper;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

@Autowired CustomerMapper customerMapper;

}
