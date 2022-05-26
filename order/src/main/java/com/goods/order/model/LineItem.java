package com.goods.order.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lines_item")
public class LineItem implements java.io.Serializable {

	private Long idlinesItem;
	private Cart cart;
	private Long idProduct;
	private Order order;
	private Integer quantity;
	private BigDecimal price;

	public LineItem() {
	}

	public LineItem(Cart cart, Long idProduct, Integer quantity, BigDecimal price) {
		this.cart = cart;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idlines_item", unique = true, nullable = false)
	public Long getIdlinesItem() {
		return this.idlinesItem;
	}

	public void setIdlinesItem(Long idlinesItem) {
		this.idlinesItem = idlinesItem;
	}
	
	
	@Column(name = "idproduct", nullable = false)
	public Long getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcart", nullable = false)
	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idorder")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price", nullable = false, precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
