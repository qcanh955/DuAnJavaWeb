package com.javaweb.controller.client;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.common.Handle;
import com.javaweb.entities.Order;
import com.javaweb.entities.OrderDetail;
import com.javaweb.repository.OrderRepository;
import com.javaweb.service.client.CCategoryServiceImpl;
import com.javaweb.service.client.COrderDetailService;
import com.javaweb.service.client.COrderService;
import com.javaweb.service.client.CProductService;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private CCategoryServiceImpl categoryService;
	
	@Autowired
	CProductService _productService;
	
	@Autowired
	COrderService orderService;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	COrderDetailService orderDetailService;
	
	@GetMapping("/xu-li-hoa-don/dang-xu-li")
	public String Xulihoadon(HttpSession session, ModelMap model) {
		Order email = (Order) session.getAttribute("CUSTOMER");
		List<OrderDetail> list = (List<OrderDetail>) this.orderDetailService.getDangXuLi(email);
		if (list == null) {
			return "redirect:/bookshop";
		}

		model.addAttribute("XULIDONHANG", list);
		return "xu-li-don-hang";
	}
	
	@GetMapping("/xu-li-hoa-don")
	public String Danhsachhoadon(HttpSession session, ModelMap model) {
		Order email = (Order) session.getAttribute("CUSTOMER");
		List<OrderDetail> list = (List<OrderDetail>) this.orderDetailService.getAllHoaDonbyidO(email);
		if (list == null) {
			return "redirect:/bookshop";
		}

		model.addAttribute("XULIDONHANG", list);
		return "xu-li-don-hang";
	}
	
	@GetMapping("/cap-nhat-hoa-don/da-giao-hang")
	public String capnhapdonhangdagiao(OrderDetail id_orders, ModelMap model, HttpSession session) {
//		id_orders.setProducts(id_orders.getProducts());
		//Order id = (Order) session.getAttribute("CUSTOMER");
		//List<OrderDetail> list = (List<OrderDetail>) this.orderDetailService.selectOrderDetailDaGiaoHang(id_orders);
		this.orderDetailService.updateOrderDetailDaGiaoHang(id_orders);
		//return "redirect:/bookshop";
		//model.addAttribute("XULIDONHANG", list);
		return "redirect:/cap-nhat-thanh-cong";
	}
	
	@RequestMapping("/cap-nhat-thanh-cong")
	public String tt() {
		return "confirmOrder";
	}
	
	@GetMapping("/xu-li-hoa-don/dang-giao")
	public String Danggiaohoadon(HttpSession session, ModelMap model) {
		Order email = (Order) session.getAttribute("CUSTOMER");
		List<OrderDetail> list = (List<OrderDetail>) this.orderDetailService.getDangGiaoHang(email);
		if (list == null) {
			return "redirect:/bookshop";
		}

		model.addAttribute("XULIDONHANG", list);
		return "xu-li-don-hang";
	}
	
	@GetMapping("/xu-li-hoa-don/da-giao")
	public String Dagiaohoadon(HttpSession session, ModelMap model) {
		Order email = (Order) session.getAttribute("CUSTOMER");
		List<OrderDetail> list = (List<OrderDetail>) this.orderDetailService.getDaGiaoHang(email);
		if (list == null) {
			return "redirect:/bookshop";
		}

		model.addAttribute("XULIDONHANG", list);
		return "xu-li-don-hang";
	}
	
	@RequestMapping("/thong-tin-ca-nhan")
	public String viewthongtin() {
		return "person-Info";
	}
	
	@RequestMapping("/gioi-thieu")
	public String gioithieu() {
		return "about-us";
	}
	

	@PostMapping(value = "/capnhap-thong-tin-ca-nhan")
	public String capnhap(Order o) {
		
		o.setMatkhau(Handle.resolutionEncrypt(o.getMatkhau()));
		this.orderService.updateOrder(o);
		//return "redirect:/bookshop";
		return "update-personInfo";
	}


	@RequestMapping("/bookshop")
	public ModelAndView Index() {
		_mvShare.addObject("categorys",  _homeService.GetDataCategory());
		_mvShare.addObject("new_products",  _homeService.NewDataProducts());
		_mvShare.addObject("highlight",  _homeService.HighLightDataProducts());
		_mvShare.addObject("sale",  _homeService.GetSale());
		_mvShare.setViewName("index");
		return _mvShare;
	}
	
	@RequestMapping(value = "/san-pham/{id}")
	public ModelAndView ShowProduct(@PathVariable String id) {
		_mvShare.setViewName("category");
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("products", categoryService.GetAllProductsByID(Integer.parseInt(id)));
		_mvShare.addObject("categorys", _homeService.GetDataCategory());
		return _mvShare;
	}
	
	@RequestMapping("/chi-tiet-san-pham/{id}")
	public ModelAndView Products(@PathVariable long id) {
		_mvShare.setViewName("product_details");
		_mvShare.addObject("product", _productService.GetProductByID(id));
		int idCategory = _productService.GetProductByID(id).getId_category();
		_mvShare.addObject("productByIDCategory", _productService.GetProductByIDCategory(idCategory));
		return _mvShare;
	}
	
}
