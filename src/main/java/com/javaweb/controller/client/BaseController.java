package com.javaweb.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.service.client.HomeService;

@Controller
public class BaseController {

	@Autowired
	HomeService _homeService;
	public ModelAndView _mvShare = new ModelAndView();

}
