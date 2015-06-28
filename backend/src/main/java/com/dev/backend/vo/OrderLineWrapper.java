package com.dev.backend.vo;

import com.dev.backend.model.OrderLine;

public class OrderLineWrapper extends OrderLine {

    private String ProductCode;

    public String getProductCode() {
	return ProductCode;
    }

    public void setProductCode(String productCode) {
	ProductCode = productCode;
    }

}
