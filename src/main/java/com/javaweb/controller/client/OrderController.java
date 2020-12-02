package com.javaweb.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entities.Order;
import com.javaweb.service.client.COrderDetailService;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

	@Autowired
	COrderDetailService orderDetailService;
	
	@RequestMapping("/")
	public String donhang(Model model) {
		model.addAttribute("LIST_DONHANG", orderDetailService.findAll());
		return "view-order";
	}
	

	@ModelAttribute(name = "ORDERS")
	public List<Order> getAllOrder() {
		return orderDetailService.findAllOrders();
	}
}
