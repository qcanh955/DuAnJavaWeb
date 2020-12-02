package com.javaweb.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaweb.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value="select id, address, email, note, phone, full_name, quantity, total, status\r\n" + 
			"from orders\r\n" + 
			"where id = ?1",nativeQuery = true)
	List<Order> findAllOrderID(long id);
	
	@Query(value = "SELECT * FROM orders WHERE email = ?", nativeQuery = true)
	Order findByEmail(String order_emal);
	
	@Query(value = "SELECT * FROM orders WHERE id = ?", nativeQuery = true)
	Order findById(long id);
	
	@Modifying
	@Query(value = "UPDATE public.orders SET full_name=?, address=?, email=?, phone=?, matkhau=?, "
			+ "quantity=?, total=?, status=?, note=?  WHERE id =?;", nativeQuery = true)
	void updateOrder(
			@Param("full_name") String full_name, 
			@Param("address") String address, 
			@Param("email") String email, 
			@Param("phone") String phone, 
			@Param("matkhau") String matkhau,
			@Param("quantity") int quantity, 
			@Param("total") double total,
			@Param("status") int status, 
			@Param("note") String note,
			@Param("id") long id);

}
