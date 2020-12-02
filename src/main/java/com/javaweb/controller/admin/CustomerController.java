package com.javaweb.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.common.Handle;
import com.javaweb.entities.Order;
import com.javaweb.service.client.COrderService;


@Controller
//@RequestMapping("/khachhang")
public class CustomerController {

	@Autowired
	COrderService orderService;

	@RequestMapping("/khachhang/list")
	public String list(ModelMap model, HttpSession session,HttpServletRequest request) {
		model.addAttribute("KhachHang", orderService.findAll());
		request.getSession().setAttribute("khachhhanglist", null);
		return "redirect:/khachhang/page/1";

	}
	
	@GetMapping("/khachhang/page/{pageNumber}")
	public String showEmployeePage(Model mode, HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("khachhhanglist");
		int pagesize = 5;
		List<Order> list = orderService.findAll();
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
		request.getSession().setAttribute("khachhhanglist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/khachhang/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("KhachHang", pages);

		return "view-customer";
	}
	
	@RequestMapping("/khachhang/delete/{id}")
	public String delete(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {
		orderService.deleteById(id);
		request.getSession().setAttribute("order", null);
		model.addAttribute("KhachHang", orderService.findAll());
		return "redirect:/khachhang/list";
	}

//	@RequestMapping("/dang-ky")
//	public String view(ModelMap model) {
//		Order orders = new Order();
//		model.addAttribute("Order", orders);
//		model.addAttribute("ACTION", "/khachhang/update");
//		return "register";
//
//	}

//	@PostMapping("/khachhang/save")
//	public String save(@Valid @ModelAttribute("Order") Order orders, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			return "register-khachhang";
//		} else {
//			orders.setMatkhau(Handle.resolutionEncrypt(orders.getMatkhau()));
//			orderService.save(orders);
//			return "register-khachhang";
//		}
//	}

	@PostMapping("/khachhang/update")
	public String Update(@Valid @ModelAttribute("Orders") Order orders, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		} else {
			orders.setMatkhau(Handle.resolutionEncrypt(orders.getMatkhau()));
			orderService.save(orders);
			return "redirect:/bookshop";
		}
	}

//	@RequestMapping("/khachhang/edit/{id}")
//	public String edit(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {
//
//		Optional<Order> u = orderService.findById(id);
//		if (u.isPresent()) {
//			model.addAttribute("Orders", u.get());
//			request.getSession().setAttribute("order", null);
//		} else {
//			model.addAttribute("Orders", new Order());
//		}
//		model.addAttribute("ACTION", "/khachhang/update");
//		return "register-changeInfo";
//	}

	
}