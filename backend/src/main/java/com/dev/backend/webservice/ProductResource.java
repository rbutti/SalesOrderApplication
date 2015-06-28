package com.dev.backend.webservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.model.Product;
import com.dev.backend.service.ProductService;
import com.dev.backend.vo.CommonResponse;

@RestController
@RequestMapping(value = "/products",
	consumes = "application/json",
	produces = "application/json")
public class ProductResource {

    static final Logger logger = Logger.getLogger(ProductResource.class);
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {

	logger.debug("createProduct() | Persistent Object: " + product.toString());
	return productService.createProduct(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> listProducts() {
	return productService.getProducts();
    }

    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public Product viewProduct(@PathVariable String code) {

	logger.debug("viewProduct() | Product code: " + code);
	return productService.getProduct(code);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.DELETE)
    public CommonResponse deleteProduct(@PathVariable String code) {

	logger.debug("deleteProduct() | Product code: " + code);
	String message = productService.deleteProduct(code);
	return new CommonResponse(message);
    }

}
