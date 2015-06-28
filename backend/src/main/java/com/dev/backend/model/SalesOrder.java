package com.dev.backend.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SALES_ORDER")
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sales_order_id")
    private Long id;
    @Column(name = "order_number", nullable = false)
    private String orderNumber;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "salesOrder", orphanRemoval = true, targetEntity = OrderLine.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderLine> orderLines;

    public SalesOrder(String orderNumber, Customer customer, BigDecimal totalPrice, List<OrderLine> orderLines) {
	this.orderNumber = orderNumber;
	this.customer = customer;
	this.totalPrice = totalPrice;
	this.orderLines = orderLines;
    }

    public SalesOrder(String orderNumber, Customer customer, BigDecimal totalPrice) {
	this.orderNumber = orderNumber;
	this.customer = customer;
	this.totalPrice = totalPrice;
    }

    public SalesOrder() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getOrderNumber() {
	return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
	this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
	return customer;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    public BigDecimal getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
    }

    public List<OrderLine> getOrderLines() {
	return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
	this.orderLines = orderLines;
    }
}
