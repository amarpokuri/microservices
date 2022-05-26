package com.goods.order.domain;

import java.util.ArrayList;
import java.util.List;

public class Category{

	
	private Integer idCategory;
	private String description;
	private List<Product> products = new ArrayList<Product>();
	public Integer getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	

}
