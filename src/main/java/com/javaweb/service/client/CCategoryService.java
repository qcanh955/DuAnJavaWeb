package com.javaweb.service.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.dto.ProductsDto;

@Service
public interface CCategoryService {
	
	public List<ProductsDto> GetAllProductsByID(int id);
	
}
