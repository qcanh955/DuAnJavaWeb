package com.javaweb.service.client;

import java.util.List;
import java.util.Optional;

import com.javaweb.entities.Order;
import com.javaweb.entities.OrderDetail;
import com.javaweb.entities.Product;

public interface COrderDetailService {

	List<Order> findAllOrders();

	void deleteById(Long id);

	long count();

	Optional<OrderDetail> findById(Long id);

	List<OrderDetail> findAll();

	OrderDetail save(OrderDetail entity);

	List<OrderDetail> getDaGiaoHang(Order email);

	List<OrderDetail> getDangGiaoHang(Order email);

	List<OrderDetail> getDangXuLi(Order email);

	List<Product> findAllProducts();

	void updateOrderDetailDaGiaoHang(OrderDetail id_orders);

	List<OrderDetail> getAllHoaDonbyidO(Order email);

//	List<OrderDetail> selectOrderDetailDaGiaoHang(OrderDetail id_orders);

	

}
