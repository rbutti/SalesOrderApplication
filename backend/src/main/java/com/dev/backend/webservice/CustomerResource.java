package com.dev.backend.webservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.model.Customer;
import com.dev.backend.service.CustomerService;
import com.dev.backend.vo.CommonResponse;

@RestController
@RequestMapping(value = "/customers",
	consumes = "application/json",
	produces = "application/json")
public class CustomerResource {

    static final Logger logger = Logger.getLogger(CustomerResource.class);
    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer customer) {

	logger.debug("createCustomer() | Persistent Object: " + customer.toString());
	return customerService.createCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers() {
	return customerService.getCustomers();
    }

    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public Customer viewCustomer(@PathVariable String code) {

	logger.debug("viewCustomer() | Customer code: " + code);
	return customerService.getCustomer(code);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.DELETE)
    public CommonResponse deleteCustomer(@PathVariable String code) {

	logger.debug("deleteCustomer() | Customer code: " + code);
	String message = customerService.deleteCustomer(code);
	return new CommonResponse(message);
    }

}
