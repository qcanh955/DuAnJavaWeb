package com.javaweb.controller.client;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.common.Handle;
import com.javaweb.dto.CartDto;
import com.javaweb.entities.Customer;
import com.javaweb.entities.Order;
import com.javaweb.entities.OrderDetail;
import com.javaweb.entities.Product;
import com.javaweb.repository.CartDao;
import com.javaweb.repository.OrderDao;
import com.javaweb.repository.OrderRepository;
import com.javaweb.repository.ProductRepository;
import com.javaweb.service.admin.ProductService;
import com.javaweb.service.client.COrderDetailService;
import com.javaweb.service.client.COrderService;
import com.javaweb.service.client.COrderServiceImpl;
import com.javaweb.service.client.CartServiceImpl;

@Controller
public class CartsController extends BaseController{
	
	@Autowired
	private CartServiceImpl cartService = new CartServiceImpl();

	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrderDao ordersDao;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private COrderServiceImpl ordersServiceImpl = new COrderServiceImpl();

	@Autowired
	COrderService orderService;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	COrderDetailService orderDetailService;
	

//	@RequestMapping("/thong-tin-khach-hang")
//	public String thongtin(ModelMap model) {
//
//		return "customerInfo";
//	}

	@RequestMapping("/thay-doi-thong-tin/{id}")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {

		Optional<Order> od = orderService.findById(id);
		if (od.isPresent()) {
			model.addAttribute("Orders", od.get());
			request.getSession().setAttribute("order", null);
		} else {
			model.addAttribute("Orders", new Order());
		}
		model.addAttribute("ACTION", "/khachhang/update");
		return "register-changeInfo";
	}
	
	@RequestMapping("/dang-ky")
	public String view(ModelMap model) {
		Order orders = new Order();
		model.addAttribute("Order", orders);
		model.addAttribute("ACTION", "/khachhang/update");
		return "register";

	}
	
	@GetMapping("/dang-nhap")
	public String login(ModelMap model) {
		Order orders = new Order();
		model.addAttribute("ORDER", orders);
		model.addAttribute("ACTION", "/dang-ki");
		return "login";
	}
	
	@PostMapping("/dang-ki")
	public String Dangki(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute("ORDER") Order orders,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "login";
		} else {
			orders.setMatkhau(Handle.resolutionEncrypt(orders.getMatkhau()));
			orders.setStatus((Integer) 1);
			orderService.save(orders);
			model.addAttribute("success", "Bạn đã đăng kí tài khoản thành công!");

//		return "redirect:" + request.getHeader("Referer");
			return "login";
		}
	}
	
	@GetMapping("/dang-xuat")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("CUSTOMER");
//		return "redirect:" + request.getHeader("Referer");
		session.removeAttribute("Cart");
		session.removeAttribute("TotalQuantyCart");
		session.removeAttribute("TotalPriceCart");
		return "redirect:/bookshop";
	}
	
	@PostMapping(value = "/kiemtra-dangnhap")
	public String checkLogin(ModelMap model, HttpSession session, @RequestParam("email") String email,
			@RequestParam("matkhau") String matkhau) {

		if (this.orderService.checkLogin(email, matkhau)) {
			session.setAttribute("CUSTOMER", this.orderRepository.findByEmail(email));
//		//	model.addAttribute("success", "Bạn đã đăng kí tài khoản thành công!");
			System.out.println("đăng nhập thành công");
			return "redirect:/bookshop";
		} else {
			System.out.println("đăng nhập thất bại");
			model.addAttribute("success", "Đăng nhập thất bại");
		}
		return "redirect:/dang-nhap";
		
	}
	
	@RequestMapping(value = "gio-hang")
	public ModelAndView Index() {
//		_mvShare.addObject("categorys", _homeService.GetDataCategory());
//		_mvShare.addObject("highLightProduct", _homeService.HighLightDataProduct());
//		_mvShare.addObject("newProduct", _homeService.NewDataProduct());
		_mvShare.setViewName("view-cart");
		return _mvShare;
	}

	@RequestMapping(value = "AddCart/{id}")
	public String AddCart(HttpServletRequest request, HttpSession session, @PathVariable long id) {
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		cart = cartService.AddCart(id, cart);

		session.setAttribute("Cart", cart);
		// model.addAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}

	
	@RequestMapping("/update")
    public String update(HttpSession session, @RequestParam("id") Long id, @RequestParam("qty") int quanty,	HttpServletRequest request){
    	Optional<Product> carts = productService.findById(id);
    	HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		cart = cartService.EditCart(id, quanty, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
    }
	

	@RequestMapping(value = "DeleteCart/{id}")
	public String DeleteCart(HttpServletRequest request, HttpSession session, @PathVariable long id) {
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		cart = cartService.DeleteCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}



	
	@GetMapping(value = "/checkout")
	public String viewCheckout(ModelMap model, HttpSession session) {
		if (session.getAttribute("CUSTOMER") == null && session.getAttribute("INFO") == null) {
			Order orders = new Order();
			model.addAttribute("ACTION", "/savecheckout");
			model.addAttribute("orders", orders);
			return "checkout";
		}
		return "pay";
	}

	@PostMapping("/savecheckout")
	public String CheckOutOrder(HttpSession session, @ModelAttribute("orders") Order order) {
		session.setAttribute("INFO", order);
		order.setQuantity((int) session.getAttribute("TotalQuantyCart"));
		order.setTotal((double) session.getAttribute("TotalPriceCart"));
		order.setStatus((Integer) 1);
		order.setMatkhau(Handle.resolutionEncrypt(order.getMatkhau()));
//		orderService.AddOrder(order) ;
		session.removeAttribute("Cart");
		session.removeAttribute("TotalQuantyCart");
		session.removeAttribute("TotalPriceCart");
		return "redirect:/dang-nhap";
	}
	
	@PostMapping("/pay")
	public String chekoutPay(HttpSession session, @RequestParam("id") Order id) {
		HashMap<Long, CartDto> cartItems = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}	
		for (Map.Entry<Long, CartDto> itemCart : cartItems.entrySet()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrders(id);
			orderDetail.setProducts(productRepository.findById(itemCart.getValue().getProduct().getId_product()).get());
			orderDetail.setQuantity(itemCart.getValue().getQuanty());
			orderDetail.setTotal(itemCart.getValue().getTotalPrice());
			orderDetail.setStatus((Integer) 1);
			orderDetail.setDatetime(LocalDateTime.now());
			ordersDao.AddOrdersDetail(orderDetail);
			
		}
		session.removeAttribute("Cart");
		session.removeAttribute("TotalQuantyCart");
		session.removeAttribute("TotalPriceCart");
		cartItems = new HashMap<>();
		return "redirect:/pay-successful";
	}
	
	@RequestMapping("/pay-successful")
	public String tt() {
		return "pay-successful";
	}

}
