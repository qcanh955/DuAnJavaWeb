//package com.javaweb.controller.client;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.javaweb.service.client.CProductService;
//import com.javaweb.service.admin.ProductServiceImpl;
//
//@Controller
//public class ProductsController extends BaseController {
//
//	@Autowired
//	CProductService _productService;
//	
//	@Autowired
//	ProductServiceImpl productService;
//	
//
//	@RequestMapping("/chi-tiet-san-pham/{id}")
//	public ModelAndView Index(@PathVariable long id) {
//		_mvShare.setViewName("product_details");
//		_mvShare.addObject("product", _productService.GetProductByID(id));
//		int idCategory = _productService.GetProductByID(id).getId_category();
//		_mvShare.addObject("productByIDCategory", _productService.GetProductByIDCategory(idCategory));
//		return _mvShare;
//	}
//	
////	@RequestMapping("/chi-tiet-san-pham/{id}")
////	public String product(@PathVariable("id") Long id, Model model) {
////		Product product = service.get(id);
////		_mvShare.addObject("new_products",  _homeService.NewDataProducts());
////		model.addAttribute("product", product);
////		return "product_details";
////	}
//	
//	
//}
