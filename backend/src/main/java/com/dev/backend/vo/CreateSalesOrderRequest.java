package com.dev.backend.vo;

import java.util.List;

import com.dev.backend.model.SalesOrder;

public class CreateSalesOrderRequest extends SalesOrder {

    private String customerCode;
    private List<OrderLineWrapper> orderLinesWrapper;

    public String getCustomerCode() {
	return customerCode;
    }

    public void setCustomerCode(String customerCode) {
	this.customerCode = customerCode;
    }

    public List<OrderLineWrapper> getOrderLinesWrapper() {
	return orderLinesWrapper;
    }

    public void setOrderLinesWrapper(List<OrderLineWrapper> orderLinesWrapper) {
	this.orderLinesWrapper = orderLinesWrapper;
    }

}
