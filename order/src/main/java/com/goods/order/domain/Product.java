package com.goods.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product  {

	
	private Long idProduct;
	private Category category;
	private String description;
	private BigDecimal price;
	private List<LineItem> linesItems = new ArrayList<LineItem>();
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public List<LineItem> getLinesItems() {
		return linesItems;
	}
	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}



}
