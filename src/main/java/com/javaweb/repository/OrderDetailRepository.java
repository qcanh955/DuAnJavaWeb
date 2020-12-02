package com.javaweb.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaweb.entities.OrderDetail;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	@Query(value = "SELECT * FROM orderdetails od LEFT JOIN orders o \n" + 
			"ON o.id = od.id_orders WHERE o.email = ? AND od.status = 1", nativeQuery = true)
	List<OrderDetail> getDangXuLi(@Param("email") String email);
	
	@Query(value = "SELECT * FROM orderdetails od LEFT JOIN orders o \n" + 
			"ON o.id = od.id_orders WHERE o.email = ? AND od.status = 2", nativeQuery = true)
	List<OrderDetail> getDangGiaoHang(@Param("email") String email);
	
	@Query(value = "SELECT * FROM orderdetails od LEFT JOIN orders o \n" + 
			"ON o.id = od.id_orders WHERE o.email = ? AND od.status = 3", nativeQuery = true)
	List<OrderDetail> getDaGiaoHang(@Param("email") String email);
	
	@Query(value = "select * from orderdetails where id_orders = ?", nativeQuery = true)
	List<OrderDetail> selectOrderDetailDaGiaoHang( @Param("id_orders") OrderDetail id_orders);
	
	@Query(value = "update public.orderdetails set status = 3 where id_orders = ?", nativeQuery = true)
	void updateOrderDetailDaGiaoHang( @Param("id_orders") OrderDetail id_orders);
	
	@Query(value = "SELECT * FROM orderdetails od LEFT JOIN orders o \n" + 
			"ON o.id = od.id_orders WHERE o.email = ? ", nativeQuery = true)
	List<OrderDetail> getAllHoaDonbyidO(@Param("email") String email);
}
