package com.javaweb.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entities.Order;
import com.javaweb.entities.OrderDetail;
import com.javaweb.entities.Product;
import com.javaweb.service.client.COrderDetailService;

@Controller
@RequestMapping("/admin/hoadon")
public class HoaDonController {
	
	@Autowired
	COrderDetailService orderDetailService;
	
//	@RequestMapping("/")
//	public String hoadon(Model model) {
//		model.addAttribute("LIST_HOADON", orderDetailService.findAll());
//		return "view-order";
//	}
	@RequestMapping("/list")
	public String list(ModelMap model, HttpSession session,HttpServletRequest request) {
		model.addAttribute("HoaDon", orderDetailService.findAll());
		request.getSession().setAttribute("hoadonlist", null);
		return "redirect:/admin/hoadon/page/1";

	}
	
	@GetMapping("/page/{pageNumber}")
	public String showEmployeePage(Model mode, HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("hoadonlist");
		int pagesize = 5;
		List<OrderDetail> list = orderDetailService.findAll();
		System.out.println(list.size());

		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("hoadonlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/hoadon/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("HoaDon", pages);

		return "view-order";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {
		orderDetailService.deleteById(id);
		request.getSession().setAttribute("orderdetail", null);
		model.addAttribute("HoaDon", orderDetailService.findAll());
		return "redirect:/admin/hoadon/list";
	}
	

	@ModelAttribute(name = "ORDERS")
	public List<Order> getAllOrders() {
		return orderDetailService.findAllOrders();
	}
	
	@ModelAttribute(name = "PRODUCT")
	public List<Product> findAllProducts() {
		return orderDetailService.findAllProducts();
	}
}
