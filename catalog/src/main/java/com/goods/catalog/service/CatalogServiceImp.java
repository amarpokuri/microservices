package com.goods.catalog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.goods.catalog.exception.ProductNotFoundException;
import com.goods.catalog.model.Product;
import com.goods.catalog.repo.ProductDao;

@Service
@Transactional
public class CatalogServiceImp implements CatalogService {

	@Autowired
	ProductDao productDao;

	@Override
	public Product findBy(Long idProduct) throws ProductNotFoundException {
		Optional<Product> product = productDao.findById(idProduct);
		if (product.isPresent())
			return product.get();
		else
			throw new ProductNotFoundException();
	}

	@Override
	public Product findBy(String description) throws ProductNotFoundException {
		List<Product> product = productDao.findByDescription(description);
		if (!CollectionUtils.isEmpty(product))
			return product.get(0);
		else
			throw new ProductNotFoundException();
	}

	@Override
	public List<Product> findByCategory(String category) throws ProductNotFoundException {
		List<Product> products = productDao.findByCategory(category);
		if (products.isEmpty() || products == null)
			throw new ProductNotFoundException();
		else
			return products;
	}

	@Override
	public List<Product> findAll() throws ProductNotFoundException {
		List<Product> products = productDao.findAll();
		if (products.isEmpty() || products == null)
			throw new ProductNotFoundException();
		else
			return products;
	}

}
