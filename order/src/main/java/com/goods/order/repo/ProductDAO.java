package com.goods.order.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.goods.order.domain.Product;

@Repository
public class ProductDAO {
	
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 public Product getProduct(Long id) {
		 return restTemplate.getForObject("http://product/products/"+id, Product.class);
	 }
	

}
