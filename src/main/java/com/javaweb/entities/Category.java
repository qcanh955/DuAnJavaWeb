package com.javaweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	@Column(name = "theloai")
	@Length(min = 6, max = 50, message = "độ dài phải từ 6 đến 50")
	private String theloai;

	public Category() {
		super();
	}

	public Category(Integer id, String theloai) {
		super();
		this.id = id;
		this.theloai = theloai;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTheloai() {
		return theloai;
	}

	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}
	
	

}
