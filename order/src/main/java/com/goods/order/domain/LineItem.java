package com.goods.order.domain;

import java.math.BigDecimal;

public class LineItem  {

	private Long idlinesItem;
	private Cart cart;
	private Product product;
	private Order order;
	private Integer quantity;
	private BigDecimal price;

	public LineItem() {
	}

	public LineItem(Cart cart, Product product, Integer quantity, BigDecimal price) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getIdlinesItem() {
		return idlinesItem;
	}

	public void setIdlinesItem(Long idlinesItem) {
		this.idlinesItem = idlinesItem;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}
