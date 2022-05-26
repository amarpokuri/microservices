package com.goods.catalog.service;

import java.util.List;

import com.goods.catalog.exception.ProductNotFoundException;
import com.goods.catalog.model.Product;

public interface CatalogService {

	Product findBy(Long idProduct) throws ProductNotFoundException;
	Product findBy(String description) throws ProductNotFoundException;
	List<Product> findByCategory(String category) throws ProductNotFoundException;
	List<Product> findAll() throws ProductNotFoundException;
	
}
