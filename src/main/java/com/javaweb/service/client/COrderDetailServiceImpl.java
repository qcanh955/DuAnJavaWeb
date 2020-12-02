package com.javaweb.service.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entities.Order;
import com.javaweb.entities.OrderDetail;
import com.javaweb.entities.Product;
import com.javaweb.repository.OrderDetailRepository;
import com.javaweb.repository.OrderRepository;
import com.javaweb.repository.ProductRepository;

@Service
public class COrderDetailServiceImpl implements COrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public OrderDetail save(OrderDetail entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public long count() {
		return orderDetailRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public List<Order> findAllOrders(){
		return (List<Order>)orderRepository.findAll();
	}
	
	@Override
	public List<Product> findAllProducts() {
		return (List<Product>)productRepository.findAll();
	}

	@Override
	public List<OrderDetail> getDangXuLi(Order email) {
		return orderDetailRepository.getDangXuLi(email.getEmail());
	}

	@Override
	public List<OrderDetail> getDangGiaoHang(Order email) {
		return orderDetailRepository.getDangGiaoHang(email.getEmail());
	}

	@Override
	public List<OrderDetail> getDaGiaoHang(Order email) {
		return orderDetailRepository.getDaGiaoHang(email.getEmail());
	}

	@Override
	public void updateOrderDetailDaGiaoHang(OrderDetail id_orders) {
		orderDetailRepository.updateOrderDetailDaGiaoHang(id_orders);
		
		
	}

	@Override
	public List<OrderDetail> getAllHoaDonbyidO(Order email) {
		return orderDetailRepository.getAllHoaDonbyidO(email.getEmail());
	}

//	@Override
//	public List<OrderDetail> selectOrderDetailDaGiaoHang(OrderDetail id_orders) {
//		orderDetailRepository.selectOrderDetailDaGiaoHang(id_orders);
//	}
	
	
	



}
