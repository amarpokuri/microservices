package com.goods.catalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goods.catalog.exception.ProductNotFoundException;
import com.goods.catalog.model.Product;
import com.goods.catalog.service.CatalogService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@RestController
public class CatalogController {

	@Autowired
	CatalogService productService;
	
	@GetMapping("/products")
	@HystrixCommand(fallbackMethod = "getFromFallBackProducts", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	public @ResponseBody ResponseEntity<List<Product>> getProducts() throws ProductNotFoundException {
		List<Product> products = productService.findAll();
		return new ResponseEntity<List<Product>> (products, HttpStatus.FOUND);
	}
	
	@GetMapping("/products/{idProduct}")
	@HystrixCommand(fallbackMethod = "getFromFallBackgetBy", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	public Product  getBy(@PathVariable("idProduct") Long idProduct) throws ProductNotFoundException  {
		Product product = productService.findBy(idProduct);
		return product;
	}

	@GetMapping("/products/description/{description}")
	@HystrixCommand(fallbackMethod = "getFromFallBackByDescription", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	public @ResponseBody ResponseEntity<Product> getByDescription(@PathVariable("description") String description) throws ProductNotFoundException  {
		Product product = productService.findBy(description);
		return new ResponseEntity<Product> (product, HttpStatus.OK);
	}

	@GetMapping("/productbycategory")
	@HystrixCommand(fallbackMethod = "getFromFallBackByCategory", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	public @ResponseBody ResponseEntity<List<Product>> getByCategory(@RequestParam("category") String category) throws ProductNotFoundException  {
		List<Product> products = productService.findByCategory(category);
		return new ResponseEntity<List<Product>> (products, HttpStatus.OK);
	}
	
	public ResponseEntity<List<Product>> getFromFallBackByCategory(String category) {
		return new ResponseEntity<List<Product>> (new ArrayList<Product>(), HttpStatus.INTERNAL_SERVER_ERROR); // need to add some kind of message
	}
	
	public ResponseEntity<Product> getFromFallBackByDescription(String description) {
		return new ResponseEntity<Product> (new Product(), HttpStatus.OK); // need to add some kind of message
	}
	
	public Product getFromFallBackgetBy( Long idProduct) {
		return new Product();
	}
	
	public ResponseEntity<List<Product>> getFromFallBackProducts() {
		return new ResponseEntity<List<Product>> (new ArrayList<Product>() , HttpStatus.NOT_FOUND);
	}

	

}
