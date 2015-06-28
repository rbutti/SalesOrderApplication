package com.dev.backend.webservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.model.SalesOrder;
import com.dev.backend.service.SalesOrderService;
import com.dev.backend.vo.CommonResponse;
import com.dev.backend.vo.CreateSalesOrderRequest;

@RestController
@RequestMapping(value = "/salesorders",
	consumes = "application/json",
	produces = "application/json")
public class SalesOrderResource {

    static final Logger logger = Logger.getLogger(SalesOrderResource.class);
    @Autowired
    private SalesOrderService salesOrderService;

    @RequestMapping(method = RequestMethod.POST)
    public SalesOrder createSalesOrder(@RequestBody CreateSalesOrderRequest salesOrderRequest) {

	logger.debug("createSalesOrder() | Persistent Object: " + salesOrderRequest.toString());
	return salesOrderService.createSalesOrder(salesOrderRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SalesOrder> listSalesOrders() {
	return salesOrderService.getSalesOrders();
    }

    @RequestMapping(value = "{orderNumber}", method = RequestMethod.GET)
    public SalesOrder viewSalesOrder(@PathVariable String orderNumber) {

	logger.debug("viewSalesOrder() | SalesOrder order number: " + orderNumber);
	return salesOrderService.getSalesOrder(orderNumber);
    }

    @RequestMapping(value = "{orderNumber}", method = RequestMethod.DELETE)
    public CommonResponse deleteSalesOrder(@PathVariable String orderNumber) {

	logger.debug("deleteSalesOrder() | SalesOrder order number: " + orderNumber);
	String message = salesOrderService.deleteSalesOrder(orderNumber);
	return new CommonResponse(message);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public SalesOrder updateSalesOrder(@RequestBody SalesOrder salesorders) {

	logger.debug("updateSalesOrder() | Persistent Object: " + salesorders.toString());
	return salesOrderService.updateSalesOrder(salesorders);
    }

}
