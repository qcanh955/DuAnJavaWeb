package com.javaweb.service.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.common.Handle;
import com.javaweb.dto.CartDto;
import com.javaweb.entities.Order;
import com.javaweb.entities.OrderDetail;
import com.javaweb.repository.OrderDao;
import com.javaweb.repository.OrderRepository;

@Service
public class COrderServiceImpl implements COrderService {

	@Autowired
	private OrderDao ordersDao;
	
	@Autowired
	private OrderRepository ordersRepository;
	
	@Override
	public int AddOrder(Order order) {
		return ordersDao.AddOrders(order);
	}
	
	public int AddAnotherOrder(Order order) {
		return ordersDao.AddAnotherOrders(order);
	}
	
	@Override
	public Order save(Order entity) {
		return ordersRepository.save(entity);
	}

	@Override
	public List<Order> findAllOrderID(long id) {
		return this.ordersRepository.findAllOrderID(id);
	}
	

	@Override
	public List<Order> findAll() {
		return ordersRepository.findAll();
	}

	@Override
	public List<Order> findAllById(List<Long> ids) {
		return ordersRepository.findAllById(ids);
	}

	@Override
	public List<Order> saveAll(List<Order> entities) {
		return ordersRepository.saveAll(entities);
	}

	@Override
	public Optional<Order> findById(Long id) {
		return ordersRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return ordersRepository.existsById(id);
	}

	@Override
	public long count() {
		return ordersRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		ordersRepository.deleteById(id);
	}

	@Override
	public void delete(Order entity) {
		ordersRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		ordersRepository.deleteAll();
	}
	
	@Override
	public boolean checkLogin(String email, String matkhau) {
		Order od = this.ordersRepository.findByEmail(email);
		if (null != od && Handle.checkEncrypt(matkhau, od.getMatkhau())) {
			return true;
		}
		return false;
	}
	
	@Override
	public void updateOrder(Order o) {
		ordersRepository.updateOrder(o.getFull_name(), o.getAddress(), o.getEmail(), o.getPhone(), o.getMatkhau(), 
		 o.getStatus(), o.getTotal(), o.getQuantity(), o.getNote(), o.getId());

	}

	@Override
	public void AddOrderDetail(HashMap<Long, CartDto> carts) {
		// TODO Auto-generated method stub
		
	}

}
