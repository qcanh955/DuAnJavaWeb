package com.javaweb.repository;

import org.springframework.stereotype.Repository;

import com.javaweb.entities.Order;
import com.javaweb.entities.OrderDetail;

@Repository
public class OrderDao extends BaseDao {

	public int AddOrders(Order order) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO orders ");
		sql.append("( ");
		sql.append(" email, phone, full_name, address, total, quantity, note, matkhau, status ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append(" '" + order.getEmail() + "', ");
		sql.append(" '" + order.getPhone() + "', ");
		sql.append(" '" + order.getFull_name() + "', ");
		sql.append(" '" + order.getAddress() + "', ");
		sql.append(" '" + order.getTotal() + "', ");
		sql.append(" '" + order.getQuantity() + "', ");
		sql.append(" '" + order.getNote() + "', ");
		sql.append(" '" + order.getMatkhau() + "',");
		sql.append(" '" + order.getStatus() + "' ");
		sql.append(") ");

		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}

	public int AddAnotherOrders(Order order) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO orders ");
		sql.append("( ");
		sql.append(" full_name, total, quantity, note, status ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append(" '" + order.getFull_name() + "', ");
		sql.append(" '" + order.getTotal() + "', ");
		sql.append(" '" + order.getQuantity() + "', ");
		sql.append(" '" + order.getNote() + "', ");
		sql.append(" '" + order.getStatus() + "' ");
		sql.append(") ");

		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}

	public long GetIDLastOrders() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(id) FROM orders");
		long id = _jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, Long.class);
		return id;
	}

	public int AddOrdersDetail(OrderDetail orderDetail) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO orderdetails ");
		sql.append("( ");
		sql.append("id_product, ");
		sql.append("id_orders, ");
		sql.append("quantity, ");
		sql.append("total, ");
		sql.append("status, ");
		sql.append("datetime ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append(" '" + orderDetail.getProducts().getId() + "', ");
		sql.append(" '" + orderDetail.getOrders().getId() + "', ");
		sql.append(" '" + orderDetail.getQuantity() + "', ");
		sql.append(" '" + orderDetail.getTotal() + "', ");
		sql.append(" '" + orderDetail.getStatus() + "', ");
		sql.append(" '" + orderDetail.getDatetime() + "' ");
		sql.append(") ");

		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}

}
