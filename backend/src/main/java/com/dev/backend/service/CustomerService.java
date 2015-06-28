package com.dev.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.backend.dao.CustomerDao;
import com.dev.backend.model.Customer;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;
    static final Logger logger = Logger.getLogger(CustomerService.class);

    @Transactional
    public Customer createCustomer(Customer customer) {

	logger.debug("createCustomer() | Persistent Object: " + customer.toString());
	if (customer.getCurrentCredit() == null) {
	    customer.setCurrentCredit(new BigDecimal(0));
	}
	customerDao.save(customer);
	return customer;
    }

    @Transactional
    public List<Customer> getCustomers() {
	return customerDao.findAll();
    }

    @Transactional
    public Customer getCustomer(String code) {

	logger.debug("getCustomer() | Customer code : " + code);
	return customerDao.findByCode(code);
    }

    @Transactional
    public String deleteCustomer(String code) {

	logger.debug("deleteCustomer() | Customer code : " + code);
	Customer customer = customerDao.findByCode(code);
	customerDao.delete(customer);
	return "Success delete of customer";
    }
}
