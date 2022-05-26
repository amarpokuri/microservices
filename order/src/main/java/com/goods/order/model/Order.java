package com.goods.order.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "orders")
public class Order implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idorder;
	private Long idCustomer;
	private Date ordered;
	private String status;
	private BigDecimal total;
	private List<LineItem> linesItems = new ArrayList<LineItem>();
	
	public Order(Long idorder2, Long idCustomer2, Date ordered2, String status2, BigDecimal total2,
			List<LineItem> linesItems2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idorder", unique = true, nullable = false)
	public Long getIdorder() {
		return this.idorder;
	}

	public void setIdorder(Long idorder) {
		this.idorder = idorder;
	}
	
	@Column(name = "idcustomer", nullable = false)
	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ordered", nullable = false, length = 19)
	public Date getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Date ordered) {
		this.ordered = ordered;
	}

	@Column(name = "status", nullable = false, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "total", nullable = false, precision = 10)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<LineItem> getLinesItems() {
		return this.linesItems;
	}

	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}


	public static class BuilderOrder {

		private Long idorder;
		private Long idCustomer;
		private Date ordered;
		private String status;
		private BigDecimal total;
		private List<LineItem> linesItems = new ArrayList<LineItem>();

		public BuilderOrder setIdorder(Long idorder) {
			this.idorder = idorder;
			return this;
		}

		public BuilderOrder setIdCustomer(Long idCustomer) {
			this.idCustomer = idCustomer;
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
			Order order = new Order(this.idorder, idCustomer, this.ordered, 
					this.status, this.total, this.linesItems);
			return order;
		}
	}
}
