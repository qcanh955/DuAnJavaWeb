package com.javaweb.service.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.dto.ProductsDto;
import com.javaweb.entities.Category;
import com.javaweb.entities.Order;


@Service
public interface HomeService {

	List<ProductsDto> GetDataProductsPaginate(int id, int start, int totalPage);

	List<ProductsDto> GetSale();

	List<ProductsDto> NewDataProducts();

	List<ProductsDto> HighLightDataProducts();

	List<Order> GetDataOrderdetails(long id);

	List<Category> GetDataCategory();
	



	
}
