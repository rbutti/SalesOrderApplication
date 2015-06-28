package com.dev.backend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.backend.dao.CustomerDao;
import com.dev.backend.dao.OrderLineDao;
import com.dev.backend.dao.ProductDao;
import com.dev.backend.dao.SalesOrderDao;
import com.dev.backend.model.OrderLine;
import com.dev.backend.model.Product;
import com.dev.backend.model.SalesOrder;
import com.dev.backend.vo.CreateSalesOrderRequest;
import com.dev.backend.vo.OrderLineWrapper;

@Service
public class SalesOrderService {

    static final Logger logger = Logger.getLogger(SalesOrderService.class);

    @Autowired
    private SalesOrderDao salesOrderDao;
    @Autowired
    private OrderLineDao orderLineDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    public SalesOrder createSalesOrder(CreateSalesOrderRequest salesOrderRequest) {

	logger.debug("createSalesOrder() | Persistent Object: " + salesOrderRequest.toString());
	SalesOrder salesOrder = salesOrderDao.findByOrderNumber(salesOrderRequest.getOrderNumber());

	if (salesOrder == null) {
	    salesOrder = new SalesOrder();
	    salesOrder.setCustomer(customerDao.findByCode(salesOrderRequest.getCustomerCode()));
	    salesOrder.setOrderNumber(salesOrderRequest.getOrderNumber());
	} else {
	    salesOrder.getCustomer().setCurrentCredit(salesOrder.getCustomer().getCurrentCredit().subtract(salesOrder.getTotalPrice()));
	    salesOrderDao.delete(salesOrder);
	}
	BigDecimal totalSalesPrice = salesOrderRequest.getTotalPrice();
	BigDecimal currentCredit = (salesOrder.getCustomer().getCurrentCredit()).add(totalSalesPrice);
	if (currentCredit.compareTo(salesOrder.getCustomer().getCreditLimit()) > 0) {
	    throw new RuntimeException("CREDIT LIMIT EXCEEDED");
	}

	salesOrder.setOrderLines(new ArrayList<OrderLine>());
	salesOrder.getCustomer().setCurrentCredit(currentCredit);

	salesOrder.setTotalPrice(salesOrderRequest.getTotalPrice());
	for (OrderLineWrapper reqOrderLine : salesOrderRequest.getOrderLinesWrapper()) {

	    Product product = null;
	    if (reqOrderLine.getProductCode() == null) {
		product = reqOrderLine.getProduct();
	    } else {
		product = productDao.findByCode(reqOrderLine.getProductCode());
	    }

	    OrderLine orderLine = new OrderLine();
	    orderLine.setProduct(product);
	    if (reqOrderLine.getQuantity() > product.getQuantity()) {
		throw new RuntimeException("PRODUCT QUANTITY NOT AVAILABLE");
	    }
	    orderLine.setQuantity(reqOrderLine.getQuantity());
	    product.setQuantity(product.getQuantity() - reqOrderLine.getQuantity());
	    orderLine.setUnitPrice(reqOrderLine.getUnitPrice());
	    orderLine.setTotalPrice(reqOrderLine.getTotalPrice());
	    orderLine.setSalesOrder(salesOrder);
	    salesOrder.getOrderLines().add(orderLine);
	}

	salesOrderDao.save(salesOrder);
	return salesOrder;
    }

    @Transactional
    public List<SalesOrder> getSalesOrders() {
	return salesOrderDao.findAll();
    }

    @Transactional
    public SalesOrder getSalesOrder(String orderNumber) {

	logger.debug("getSalesOrder() | Order number: " + orderNumber);
	return salesOrderDao.findByOrderNumber(orderNumber);
    }

    @Transactional
    public String deleteSalesOrder(String orderNumber) {

	logger.debug("deleteSalesOrder() | Order number: " + orderNumber);
	SalesOrder salesOrder = salesOrderDao.findByOrderNumber(orderNumber);
	salesOrder.getCustomer().setCurrentCredit(salesOrder.getCustomer().getCurrentCredit().subtract(salesOrder.getTotalPrice()));
	for (OrderLine orderLine : salesOrder.getOrderLines()) {
	    orderLine.getProduct().setQuantity(orderLine.getProduct().getQuantity() + orderLine.getQuantity());
	}
	salesOrderDao.delete(salesOrder);
	return "Success delete of a sales order";
    }

    @Transactional
    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {

	logger.debug("updateSalesOrder() | Persistent Object: " + salesOrder.toString());
	salesOrderDao.update(salesOrder);
	return salesOrder;
    }

}
