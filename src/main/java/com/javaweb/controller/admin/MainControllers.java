package com.javaweb.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.controller.client.BaseController;
import com.javaweb.service.client.HomeService;
import com.javaweb.utils.WebUtils;

@Controller
public class MainControllers extends BaseController {

	@Autowired
	HomeService _homeService;

	@RequestMapping(value = "/bookshop", method = RequestMethod.GET)
	public ModelAndView ViewPage(Model model, Principal principal) {
		_mvShare.addObject("categorys", _homeService.GetDataCategory());
		_mvShare.addObject("new_products", _homeService.NewDataProducts());
		_mvShare.addObject("highlight", _homeService.HighLightDataProducts());
		_mvShare.addObject("sales", _homeService.GetSale());
//		String userName = principal.getName();
//
//		System.out.println("Người dùng: " + userName);
//
//		User loginedUser = (User)((Authentication) principal).getPrincipal();
//
//		String userInfo = WebUtils.toString(loginedUser);
//		model.addAttribute("userInfo", userInfo);
		_mvShare.setViewName("index");
		return _mvShare;
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "welcomePage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return "layout";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {

		return "adm-login";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}
// 
//    @RequestMapping(value = "/bookshop", method = RequestMethod.GET)
//    public String userInfo(Model model, Principal principal) {
// 
//        // Sau khi user login thanh cong se co principal
//        String userName = principal.getName();
// 
//        System.out.println("Người dùng: " + userName);
// 
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
// 
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
// 
//        return "index";
//    }

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Xin chào " + principal.getName() //
					+ "<br> Bạn không đủ quyền hạn để truy cập vào trang này!";
			model.addAttribute("message", message);

		}

		return "403";
	}

}
