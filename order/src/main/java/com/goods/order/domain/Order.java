package com.goods.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order  {

	private Long idorder;
	private Customer customer;
	private Date ordered;
	private String status;
	private BigDecimal total;
	private List<LineItem> linesItems = new ArrayList<LineItem>();

	public Order() {
	}

	public Order(Long idorder, Customer customer, Date ordered, String status, BigDecimal total, List<LineItem> linesItems) {
		this.idorder = idorder;
		this.customer = customer;
		this.ordered = ordered;
		this.status = status;
		this.total = total;
		this.linesItems = linesItems;
	}


	public Long getIdorder() {
		return this.idorder;
	}

	public void setIdorder(Long idorder) {
		this.idorder = idorder;
	}

	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public Date getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Date ordered) {
		this.ordered = ordered;
	}

	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
	public List<LineItem> getLinesItems() {
		return this.linesItems;
	}

	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}

	public static class BuilderOrder {

		private Long idorder;
		private Customer customer;
		private Date ordered;
		private String status;
		private BigDecimal total;
		private List<LineItem> linesItems = new ArrayList<LineItem>();

		public BuilderOrder setIdorder(Long idorder) {
			this.idorder = idorder;
			return this;
		}

		public BuilderOrder setCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public BuilderOrder setOrdered(Date ordered) {
			this.ordered = ordered;
			return this;
		}

		public BuilderOrder setStatus(String status) {
			this.status = status;
			return this;
		}

		public BuilderOrder setTotal(BigDecimal total) {
			this.total = total;
			return this;
		}

		public BuilderOrder setLinesItems(List<LineItem> linesItems) {
			this.linesItems = linesItems;
			return this;
		}

		public Order build() {
			Order order = new Order(this.idorder, this.customer, this.ordered, 
					this.status, this.total, this.linesItems);
			return order;
		}
	}
}
