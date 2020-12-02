package com.javaweb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.service.admin.CategoryService;

@Controller
@RequestMapping("/admin")
public class MainController {

	/*
	 * @Autowired private ProductRepository productRepository;
	 * 
	 * @Autowired ProductServiceImpl service;
	 * 
	 * @Autowired CategoryService categorySevice;
	 * 
	 * @RequestMapping("them") public String them() { return "new-product"; }
	 * 
	 * @RequestMapping({ "admin" }) public String index() { // return "index";
	 * return "layout"; }
	 * 
	 * 
	 * 
	 * @GetMapping("/403") public String accessDenied() { return "403"; }
	 * 
	 * @GetMapping("/login") public String getLogin() { return "adm-login"; }
	 * 
	 * @GetMapping("/products") public String shop(Model model) { //
	 * model.addAttribute("listproducts", productRepository.findAll());
	 * List<Product> liProducts = service.listAll();
	 * model.addAttribute("listproducts", liProducts); return "view-product"; }
	 * 
	 * @RequestMapping("/cart") public String cart() {
	 * 
	 * return "cart"; }
	 * 
	 * @RequestMapping("/product/{id}") public String product(@PathVariable("id")
	 * Long id, Model model) { Product product = service.get(id);
	 * model.addAttribute("product", product); return "product"; }
	 * 
	 * @RequestMapping("/category") public String car(ModelMap model) {
	 * model.addAttribute("LIST_CATEGORY", categorySevice.findAll()); return
	 * "view-category"; }
	 */
	@Autowired
	CategoryService categoryService;

	@RequestMapping({ "/", "/admin" })
	public String index() {
//		return "index";
		return "layout";
	}	
	
	@RequestMapping("/show")
	public String gioithieu(){
		return "about-us";
	}
}
