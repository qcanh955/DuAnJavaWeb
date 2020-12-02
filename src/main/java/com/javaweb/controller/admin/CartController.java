package com.javaweb.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.entities.Product;
import com.javaweb.models.Cart;
import com.javaweb.models.CartManage;
import com.javaweb.repository.ProductRepository;
import com.javaweb.service.admin.ProductServiceImpl;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	/*
	 * @Autowired ProductRepository productRepository;
	 * 
	 * @Autowired private CartManage cartManage;
	 * 
	 * @Autowired private ProductServiceImpl service;
	 * 
	 * @RequestMapping("/add") public String add(HttpServletRequest
	 * request,HttpSession session, @RequestParam("id") Long id,
	 * 
	 * @RequestParam(value = "qty", required = false, defaultValue = "1") int qty) {
	 * Product product = service.get(id); Cart cart = cartManage.getCart(session);
	 * cart.addItem(product, qty); request.getSession().setAttribute("cartlist",
	 * null); return "cart"; }
	 * 
	 * @RequestMapping("/remove") public String remove(HttpServletRequest
	 * request,HttpSession session, @RequestParam("id") Long id) { Product product =
	 * service.get(id); Cart cart = cartManage.getCart(session);
	 * cart.removeItem(product); request.getSession().setAttribute("cartlist",
	 * null); return "cart"; }
	 * 
	 * @RequestMapping("/update") public String update(HttpServletRequest
	 * request,HttpSession session, @RequestParam("id") Long
	 * id, @RequestParam("qty") int qty) { Product product = service.get(id); Cart
	 * cart = cartManage.getCart(session); cart.updateItem(product, qty);
	 * request.getSession().setAttribute("cartlist", null); return "cart"; }
	 * 
	 * 
	 * @GetMapping("/page/{pageNumber}") public String showEmployeePage(Model mode,
	 * HttpServletRequest request, @PathVariable int pageNumber, Model model) {
	 * PagedListHolder<?> pages = (PagedListHolder<?>)
	 * request.getSession().getAttribute("cartlist"); int pagesize = 4;
	 * List<Product> list = (List<Product>) service.listAll();
	 * mode.addAttribute("listproducts", productRepository.findAll());
	 * System.out.println(list.size());
	 * 
	 * if (pages == null) { pages = new PagedListHolder<>(list);
	 * pages.setPageSize(pagesize); } else { final int goToPage = pageNumber - 1; if
	 * (goToPage <= pages.getPageCount() && goToPage >= 0) {
	 * pages.setPage(goToPage); } } request.getSession().setAttribute("cartlist",
	 * pages); int current = pages.getPage() + 1; int begin = Math.max(1, current -
	 * list.size()); int end = Math.min(begin + 5, pages.getPageCount()); int
	 * totalPageCount = pages.getPageCount(); String baseUrl = "/cart/page/";
	 * 
	 * model.addAttribute("beginIndex", begin); model.addAttribute("endIndex", end);
	 * model.addAttribute("currentIndex", current);
	 * model.addAttribute("totalPageCount", totalPageCount);
	 * model.addAttribute("baseUrl", baseUrl); model.addAttribute("products",
	 * pages);
	 * 
	 * return "view-product"; }
	 */
}
