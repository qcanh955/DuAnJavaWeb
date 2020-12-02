package com.javaweb.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
	private Long id;
	@NotBlank(message = "không được để trống")
	private String name;
	@NotNull(message = "không được để trống")
	private int plsy;
	@NotBlank(message = "không được để trống")
	private String nxb;
	@NotBlank(message = "không được để trống")
	private String author;
	@NotNull(message = "không được để trống")
	private int pagenum;
	private MultipartFile image;
	@NotNull(message = "không được để trống")
	private Double price;
	private Integer id_category;
	private int sale;
	private boolean highlight;
	private boolean new_product;
	@NotBlank(message = "không được để trống")
	private String details;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated_at;

	public ProductDto() {
		super();
	}

	public ProductDto(Long id, String name, int plsy, String nxb, String author, int pagenum, MultipartFile image,
			Double price, Integer id_category, int sale, boolean highlight, boolean new_product, String details,
			Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.plsy = plsy;
		this.nxb = nxb;
		this.author = author;
		this.pagenum = pagenum;
		this.image = image;
		this.price = price;
		this.id_category = id_category;
		this.sale = sale;
		this.highlight = highlight;
		this.new_product = new_product;
		this.details = details;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlsy() {
		return plsy;
	}

	public void setPlsy(int plsy) {
		this.plsy = plsy;
	}

	public String getNxb() {
		return nxb;
	}

	public void setNxb(String nxb) {
		this.nxb = nxb;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getId_category() {
		return id_category;
	}

	public void setId_category(Integer id_category) {
		this.id_category = id_category;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public boolean isNew_product() {
		return new_product;
	}

	public void setNew_product(boolean new_product) {
		this.new_product = new_product;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

}
