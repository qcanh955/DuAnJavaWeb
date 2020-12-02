//package com.javaweb.controller.client;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.javaweb.service.admin.ProductService;
//import com.javaweb.service.client.CCategoryServiceImpl;
//
//@Controller
//public class CategoriesController extends BaseController {
//
//	/*
//	 * @Autowired private CCategoryServiceImpl categoryService;
//	 * 
//	 * @Autowired private PaginateServiceImpl paginateService;
//	 * 
//	 * 
//	 * 
//	 * private int totalPage = 4;
//	 * 
//	 * 
//	 * @RequestMapping(value = "/san-pham/{id}") public ModelAndView
//	 * Product(@PathVariable String id) { //int totalProductPage = 9;
//	 * _mvShare.addObject("categorys", _homeService.GetDataCategory());
//	 * _mvShare.addObject("new_products", _homeService.NewDataProducts());
//	 * _mvShare.addObject("highlight", _homeService.HighLightDataProducts());
//	 * _mvShare.addObject("sales", _homeService.SaleDataProducts());
//	 * _mvShare.setViewName("category"); int totalData =
//	 * categoryService.GetAllProductsByID(Integer.parseInt(id)).size(); PaginateDto
//	 * paginatesInfo = paginateService.GetInfoPaginates(totalData, totalPage, 1);
//	 * _mvShare.addObject("idCategory", id); _mvShare.addObject("paginateInfo",
//	 * paginatesInfo); _mvShare.addObject("products",
//	 * categoryService.GetDataProductsPaginate(Integer.parseInt(id),
//	 * paginatesInfo.getStart(), totalPage)); return _mvShare; }
//	 * 
//	 * @RequestMapping(value = "/san-pham/{id}/{currentPage}") public ModelAndView
//	 * Product(@PathVariable String id, @PathVariable String currentPage) { //int
//	 * totalProductPage = 9; _mvShare.setViewName("category"); int totalData =
//	 * categoryService.GetAllProductsByID(Integer.parseInt(id)).size(); PaginateDto
//	 * paginatesInfo = paginateService.GetInfoPaginates(totalData, totalPage,
//	 * Integer.parseInt(currentPage)); _mvShare.addObject("idCategory", id);
//	 * _mvShare.addObject("paginateInfo", paginatesInfo);
//	 * _mvShare.addObject("productsPaginate",
//	 * categoryService.GetDataProductsPaginate(Integer.parseInt(id),paginatesInfo.
//	 * getStart(), totalPage)); return _mvShare; }
//	 */
//	@Autowired
//	ProductService service;
//
//	@Autowired
//	private CCategoryServiceImpl categoryService;
//	
//
//
//	@RequestMapping(value = "/san-pham/{id}")
//	public ModelAndView Product(@PathVariable String id) {
////		_mvShare.setViewName("redirect:/page/1");
//		_mvShare.setViewName("category");
//		// lấy id_category
//		_mvShare.addObject("idCategory", id);
//		// lấy products theo id_category
//		_mvShare.addObject("products", categoryService.GetAllProductsByID(Integer.parseInt(id)));
//		// all category
//		_mvShare.addObject("categorys", _homeService.GetDataCategory());
//		return _mvShare;
//	}
//}
