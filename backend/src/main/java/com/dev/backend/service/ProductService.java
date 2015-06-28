package com.dev.backend.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.backend.dao.ProductDao;
import com.dev.backend.model.Product;

@Service
public class ProductService {

    static final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;

    @Transactional
    public Product createProduct(Product product) {

	logger.debug("createProduct() | Persistent Object: " + product.toString());
	productDao.save(product);
	return product;
    }

    @Transactional
    public List<Product> getProducts() {
	return productDao.findAll();
    }

    @Transactional
    public Product getProduct(String code) {

	logger.debug("getProduct() | Product code : " + code);
	return productDao.findByCode(code);
    }

    @Transactional
    public String deleteProduct(String code) {

	logger.debug("deleteProduct() | Product code : " + code);
	Product product = productDao.findByCode(code);
	productDao.delete(product);
	return "Success delete of product";
    }
}
