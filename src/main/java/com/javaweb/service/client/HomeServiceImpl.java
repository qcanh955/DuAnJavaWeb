package com.javaweb.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.dto.ProductsDto;
import com.javaweb.entities.Category;
import com.javaweb.entities.Order;
import com.javaweb.repository.CategoryDao;
import com.javaweb.repository.OrderRepository;
import com.javaweb.repository.ProductDao;


@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private CategoryDao categoryRepository;
	
	@Autowired
	private ProductDao productsDao;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Category> GetDataCategory() {
		return categoryRepository.GetDataCategorys();
	}

	@Override
	public List<Order> GetDataOrderdetails(long id) {
		return orderRepository.findAllOrderID(id);
	}

	@Override
	public List<ProductsDto> HighLightDataProducts() {
		List<ProductsDto> listProducts = productsDao.HighLightDataProducts();
		return listProducts;
	}

	@Override
	public List<ProductsDto> NewDataProducts() {
		List<ProductsDto> listProducts = productsDao.NewDataProducts();
		return listProducts;
	}
	
	@Override
	public List<ProductsDto> GetSale() {
		List<ProductsDto> listProducts = productsDao.GetSale();
		return listProducts;
	}
	
	@Override
	public List<ProductsDto> GetDataProductsPaginate(int id, int start, int totalPage) {
		List<ProductsDto> listProducts = productsDao.GetDataProductsPaginate(id, start, totalPage);
		return listProducts;
	}
}
