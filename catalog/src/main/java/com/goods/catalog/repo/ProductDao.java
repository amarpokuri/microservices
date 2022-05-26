package com.goods.catalog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goods.catalog.model.Product;

public interface ProductDao extends JpaRepository<Product, Long>{

	
	List<Product> findByDescription(String description);
	List<Product> findByCategory(String category);
	
}
