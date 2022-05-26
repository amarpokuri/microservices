package com.goods.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart  {

	private Long idCart;
	private Customer customer;
	private BigDecimal subtotal;
	private List<LineItem> linesItems = new ArrayList<LineItem>();

	public Cart() {
	}

	public Cart(Long idCart, Customer customer, BigDecimal subtotal) {
		this.idCart = idCart;
		this.customer = customer;
		this.subtotal = subtotal;
	}

	public Cart(Long idCart, Customer customer, BigDecimal subtotal, List<LineItem> linesItems) {
		this.idCart = idCart;
		this.customer = customer;
		this.subtotal = subtotal;
		this.linesItems = linesItems;
	}

	
	public Long getIdCart() {
		return this.idCart;
	}

	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customers) {
		this.customer = customers;
	}
	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	
	public List<LineItem> getLinesItems() {
		return this.linesItems;
	}

	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}
	
	public BigDecimal calculateTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for (LineItem lineItem : this.getLinesItems()) {
			total.add(lineItem.getPrice().multiply(new BigDecimal(lineItem.getQuantity())));		
		}
		return total;
	}
}
