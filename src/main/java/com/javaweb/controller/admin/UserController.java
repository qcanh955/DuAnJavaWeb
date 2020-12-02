package com.javaweb.controller.admin;


import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaweb.entities.ChucNang;
import com.javaweb.entities.NguoiDung;
import com.javaweb.entities.VaiTro;
import com.javaweb.service.admin.UserService;

@Controller
@RequestMapping("/admin/nguoidung")
public class UserController {

	@Autowired
	UserService userService;

	@ModelAttribute("CHUCNANGS")
	public List<ChucNang> getAllChucNang() {
		return this.userService.finAllChucNang();
	}

	@ModelAttribute("VAITROS")
	public List<VaiTro> getAllVaiTro() {
		return this.userService.finAllVaiTro();
	}

	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		NguoiDung nd = new NguoiDung();
		model.addAttribute("NGUOIDUNG", nd);
		return "add-user";
	}

	@RequestMapping(value = "/saveNguoiDung", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize("hasPermission('', 'themnd')")
	public String saveNguoiDung(NguoiDung nd, Principal principal) {
		nd.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000")));
		nd.setCreateday(new Timestamp(new Date().getTime()));
		nd.setNguoitao(principal.getName());
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setNguoiupdate(principal.getName());
		this.userService.insertNguoiDung(nd);
		return "redirect:/admin/nguoidung/list";
	}

	@GetMapping(value = "/nguoidung-update", produces = "application/json")
	@ResponseBody
	@PreAuthorize("hasPermission('', 'cnnd')")
	public Map<String, String> update(Long id, ModelMap model) {
		NguoiDung nd = this.userService.findById1(id);
		System.out.println("nguoidung:" + nd);
		List<Long> lsvt = this.userService.findByIdvaitro(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(nd.getId()));
		map.put("manguoidung", nd.getManguoidung());
		map.put("tennguoidung", nd.getTennguoidung());
		map.put("password", nd.getPassword());
		map.put("email", nd.getEmail());
		map.put("gender", String.valueOf(nd.getGender()));
		map.put("phone", nd.getPhone());

		map.put("vaitros", String.valueOf(lsvt));
		System.out.println("map day:" + map);
		return map;
	}

	@GetMapping("/nguoidung-chitiet")
	@PreAuthorize("hasPermission('', 'xctnd')")
	public Optional<NguoiDung> ChiTiet(Long id) {
		return this.userService.findNguoiDungById(id);
	}

	@RequestMapping(value = "/doUpdate", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String doUpdate(NguoiDung nd, Principal principal) {
		nd.setIsdelete((Integer) 0);
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setNguoiupdate(principal.getName());
		this.userService.updateNguoiDung(nd);
		return "redirect:/admin/nguoidung/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize("hasPermission('', 'danhsachnd')")
	public String list(ModelMap model, HttpSession session, HttpServletRequest request) {
		request.getSession().setAttribute("nguoidunglist", null);
		return "redirect:/admin/nguoidung/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.PUT })
	public String showNguoiDungPages(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoidunglist");
		int pageSize = 5;
		List<NguoiDung> list = (List<NguoiDung>) this.userService.getAllNguoiDung();
		int sum = list.size();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pageSize);
		} else {
			final int gotoPage = pageNumber - 1;
			if (gotoPage <= pages.getPageCount() && gotoPage >= 0) {
				pages.setPage(gotoPage);
			}
		}

		request.getSession().setAttribute("nguoidunglist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/list/page/";

		model.addAttribute("sum", sum);

		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("NGUOIDUNGS", pages);

		return "view-user";
	}

	@RequestMapping("/list/search/{pageNumber}")
	public String search(ModelMap model, HttpServletRequest request, @RequestParam("keyword") String tennguoidung,
			@PathVariable int pageNumber) {
		if (tennguoidung.equals("")) {
			return "redirect:/admin/nguoidung/list";
		}
		List<NguoiDung> list = this.userService.findByTennguoidung(tennguoidung);
		if (list == null) {
			return "redirect:/admin/nguoidung/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoidunglist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("nguoidunglist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/list/page/";

		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("NGUOIDUNGS", pages);
		return "view-user";
	}

	@RequestMapping("/delete")
	@PreAuthorize("hasPermission('', 'xoand')")
	public String delete(ModelMap model, @RequestParam("id[]") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			NguoiDung nd = this.userService.findNguoiDungById(long1).get();
			nd.setUpdateday(new Timestamp(new Date().getTime()));
			nd.setNguoiupdate(principal.getName());
			nd.setIsdelete((Integer) 1);
			this.userService.deleteNguoiDung(nd);
		}
		return "redirect:/admin/nguoidung/list/";
	}

	@RequestMapping("/edit/{id}")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {

		Optional<NguoiDung> u = userService.findNguoiDungById(id);
		if (u.isPresent()) {
			model.addAttribute("NGUOIDUNG", u.get());
			request.getSession().setAttribute("nguoidung", null);
		} else {
			model.addAttribute("NGUOIDUNG", new NguoiDung());
		}
		model.addAttribute("ACTION", "/nguoidung/doUpdate");
		return "update-user";
	}

	@RequestMapping("/delete/{id}")
	public String delete(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {
		NguoiDung nd = this.userService.findNguoiDungById(id).get();
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setIsdelete((Integer) 1);
		userService.deleteNguoiDung(nd);
		request.getSession().setAttribute("nguoidung", null);
		model.addAttribute("NGUOIDUNGS", userService.getAllNguoiDung());
		return "redirect:/admin/nguoidung/list/";
	}
}
