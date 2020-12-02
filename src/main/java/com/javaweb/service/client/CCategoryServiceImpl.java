package com.javaweb.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.dto.ProductsDto;
import com.javaweb.repository.ProductDao;

@Service
public class CCategoryServiceImpl implements CCategoryService {

	@Autowired
	private ProductDao productsDao;
	
	public List<ProductsDto> GetAllProductsByID(int id) {
		return productsDao.GetAllProductsByID(id);
	}

	public List<ProductsDto> GetDataProductsPaginate(int id, int start, int totalPage) {
		return productsDao.GetDataProductsPaginate(id, start, totalPage);

	}

}
