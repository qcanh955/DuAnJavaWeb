package com.javaweb.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="orderdetails")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name = "id_product")
	private Product products;
	
	@ManyToOne()
	@JoinColumn(name = "id_orders")
	private Order orders;
	
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total")
	private double total;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "datetime")
	@CreationTimestamp
	private LocalDateTime datetime;
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(long id, Product products, Order orders, int quantity, double total, Integer status,
			LocalDateTime datetime) {
		super();
		this.id = id;
		this.products = products;
		this.orders = orders;
		this.quantity = quantity;
		this.total = total;
		this.status = status;
		this.datetime = datetime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	

	

	
	
	
}
