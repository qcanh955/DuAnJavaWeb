package com.javaweb.service.client;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.javaweb.dto.CartDto;
import com.javaweb.entities.Order;

public interface COrderService {

	void deleteAll();

	void delete(Order entity);

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	Optional<Order> findById(Long id);

	List<Order> saveAll(List<Order> entities);

	List<Order> findAllById(List<Long> ids);

	List<Order> findAll();

	List<Order> findAllOrderID(long id);

	Order save(Order entity);

	void AddOrderDetail(HashMap<Long, CartDto> carts);

	int AddOrder(Order order);

	boolean checkLogin(String email, String matkhau);

	void updateOrder(Order o);

}
