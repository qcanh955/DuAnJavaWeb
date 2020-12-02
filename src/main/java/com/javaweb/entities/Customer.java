package com.javaweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email")
	@NotBlank(message = "không được để trống")
	@Email(message = "Vui lòng nhập một địa chỉ e-mail hợp lệ")
	private String email;

	@Column(name = "password")
	@NotBlank(message = "không được để trống")
	@Length(min = 6, max = 60, message = "độ dài phải từ 6 đến 60")
	private String password;

	@Column(name = "fullname")
	@Length(min = 8, max = 50, message = "độ dài phải từ 8 đến 50")
	private String fullname;

	@Column(name = "phone")
	@Length(min = 10, max = 15, message = "độ dài phải từ 10 đến 15")
	private String phone;

	@Column(name = "address")
	@NotBlank(message = "không được để trống")
	private String address;

	@Column(name = "gender")
	private boolean gender;

	public Customer() {
		super();
	}

	public Customer(int id, String email, String password, String fullname, String phone, String address,
			boolean gender) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

}
