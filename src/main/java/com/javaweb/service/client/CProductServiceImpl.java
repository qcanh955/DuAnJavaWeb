package com.javaweb.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.dto.ProductsDto;
import com.javaweb.repository.ProductDao;

@Service
public class CProductServiceImpl implements CProductService{

	@Autowired
	ProductDao productDao = new ProductDao();

	public ProductsDto GetProductByID(long id) {
		List<ProductsDto> listProducts = productDao.GetProductByID(id);
		return listProducts.get(0);
	}

	public List<ProductsDto> GetProductByIDCategory(int id) {
		return productDao.GetAllProductsByID(id);
	}

	


	
}
