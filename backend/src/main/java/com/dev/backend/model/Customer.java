package com.dev.backend.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone1", nullable = false)
    private String phone1;
    @Column(name = "phone2", nullable = false)
    private String phone2;
    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;
    @Column(name = "current_credit", nullable = false)
    private BigDecimal currentCredit;

    public Customer(Long id, String code, String name, String address, String phone1, String phone2, BigDecimal creditLimit, BigDecimal currentCredit) {
	this.id = id;
	this.code = code;
	this.name = name;
	this.address = address;
	this.phone1 = phone1;
	this.phone2 = phone2;
	this.creditLimit = creditLimit;
	this.currentCredit = currentCredit;
    }

    public Customer() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPhone1() {
	return phone1;
    }

    public void setPhone1(String phone1) {
	this.phone1 = phone1;
    }

    public String getPhone2() {
	return phone2;
    }

    public void setPhone2(String phone2) {
	this.phone2 = phone2;
    }

    public BigDecimal getCreditLimit() {
	return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
	this.creditLimit = creditLimit;
    }

    public BigDecimal getCurrentCredit() {
	return currentCredit;
    }

    public void setCurrentCredit(BigDecimal currentCredit) {
	this.currentCredit = currentCredit;
    }
}
