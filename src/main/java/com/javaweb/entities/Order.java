package com.javaweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "orders")
@Proxy(lazy = false)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "email")
	@Email(message = "Vui lòng nhập một địa chỉ e-mail hợp lệ")
	private String email;

	@Column(name = "phone")
	@NotBlank(message = "không được để trống")
	private String phone;

	@Column(name = "full_name")
	@Length(min = 2, max = 50, message = "độ dài phải từ 8 đến 50")
	private String full_name;

	@Column(name = "address")
	@NotBlank(message = "không được để trống")
	private String address;

	@Column(name = "matkhau")
	@NotBlank(message = "không được để trống")
	@Length(min = 8, max = 100, message = "độ dài phải từ 8 đến 100")
	private String matkhau;

	@Column(name = "total")
	private double total;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "note")
	private String note;

	@Column(name = "status")
	private int status;

	public Order() {
		super();
	}

	public Order(long id, String email, String phone, String full_name, String address, String matkhau, double total,
			int quantity, String note, int status) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.full_name = full_name;
		this.address = address;
		this.matkhau = matkhau;
		this.total = total;
		this.quantity = quantity;
		this.note = note;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

		
}
