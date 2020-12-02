package com.javaweb.service.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.dto.ProductsDto;

@Service
public interface CProductService {

	public ProductsDto GetProductByID(long id);

	public List<ProductsDto> GetProductByIDCategory(int id);

}
