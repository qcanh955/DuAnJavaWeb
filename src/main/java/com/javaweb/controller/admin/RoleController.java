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

import com.javaweb.common.Handle;
import com.javaweb.entities.ChucNang;
import com.javaweb.entities.VaiTro;
import com.javaweb.service.admin.ChucNangService;
import com.javaweb.service.admin.VaiTroService;

@Controller
@RequestMapping("/admin/vaitro")
public class RoleController {

	@Autowired
	VaiTroService vaiTroService;
	
	@Autowired
	ChucNangService chucNangService;
	
	@ModelAttribute("CHUCNANGS")
	public List<ChucNang> getAllChucNang(){
		return this.vaiTroService.findAllChucNang();
	}
	
	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		VaiTro vt = new VaiTro();
		model.addAttribute("VAITRO", vt);
		return "add-role";
	}
	
	@RequestMapping(value = "/saveVaitro", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	@PreAuthorize("hasPermission('', 'themvt')")
	public String saveVaiTro(ModelMap model, @ModelAttribute("VAITRO") VaiTro vt, Principal principal) {
		vt.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000")));
		vt.setCreateday(new Timestamp(new Date().getTime()));
		vt.setUpdateday(new Timestamp(new Date().getTime()));
		vt.setNguoitao(principal.getName());
		vt.setNguoiupdate(principal.getName());
		this.vaiTroService.insertVaitro(vt);
		model.addAttribute("VAITROS", this.vaiTroService.listVaiTro());
		return "redirect:/admin/vaitro/list";
	}
	
	@GetMapping(value="/update", produces = "application/json")
	@ResponseBody
	@PreAuthorize("hasPermission('', 'suavt')")
	public Map<String, String> update(Long id) {
		VaiTro vt = this.vaiTroService.findByVaitroId(id).get();
		List<Long> lscn = this.vaiTroService.findChucnangVaitro(vt.getId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(vt.getId()));
		map.put("mavaitro", vt.getMavaitro());
		map.put("tenvaitro", vt.getTenvaitro());
		map.put("chucnangs", String.valueOf(lscn));
		return map;
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {

		Optional<VaiTro> u = vaiTroService.findByVaitroId(id);
		if (u.isPresent()) {
			model.addAttribute("VAITRO", u.get());
			request.getSession().setAttribute("vaitro", null);
		} else {
			model.addAttribute("VAITRO", new VaiTro());
		}
		model.addAttribute("ACTION", "/admin/vaitro/updateVaiTro");
		return "update-role";
	}
	
	
	@GetMapping("/vaitro-chitiet")
	@PreAuthorize("hasPermission('', 'xctvt')")
	public Optional<VaiTro> chitiet(Long id) {
		return this.vaiTroService.findByVaitroId(id);
	}
	
	@RequestMapping(value = "/updateVaiTro",  method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(ModelMap model, VaiTro vt, Principal principal) {
		vt.setIsdelete((Integer) 0);
		vt.setUpdateday(new Timestamp(new Date().getTime()));
		vt.setNguoiupdate(principal.getName());
		this.vaiTroService.updateVaitro(vt);
		return "redirect:/admin/vaitro/list";
	}
	
	@GetMapping("/list")
	public String list(ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("vaitrolist", null);
		return "redirect:/admin/vaitro/list/page/1";
	}
	
	@GetMapping("/list/page/{pageNumber}")
	@PreAuthorize("hasPermission('', 'danhsachvt')")
	public String showVaiTrosPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("vaitrolist");
		int pagesize = 5;
		List<VaiTro> list = (List<VaiTro>) this.vaiTroService.listVaiTro();
		int sum = list.size();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1; 
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("vaitrolist", pages);
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
		model.addAttribute("VAITROS", pages);
		return "view-role";
	}
	
	@RequestMapping("/dataSearch")
	public String dataSearch(@RequestParam("namevt") String tenvaitro, @RequestParam("keyvt") String mavaitro, HttpSession session) {
		session.setAttribute("NAMEVT", tenvaitro);
		session.setAttribute("KEYVT", mavaitro);
		if (tenvaitro == null || tenvaitro.equals("")) {
			if (mavaitro == null || mavaitro.equals("")) {
				return "redirect:/admin/vaitro/list";
			} else {
				mavaitro = Handle.xuLySearch(mavaitro);
				session.setAttribute("KEYVT", mavaitro);
				session.setAttribute("SEARCH", 1);
				return "redirect:/admin/vaitro/list/search/1";
			}
		} else {
			tenvaitro = Handle.xuLySearch(tenvaitro);
			session.setAttribute("NAMEVT", tenvaitro);
			session.setAttribute("SEARCH", 2);
			return "redirect:/admin/vaitro/list/search/1";
		}
	}
	@RequestMapping("/list/search/{numberPage}")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int numberPage, HttpSession session) {
		String tenvaitro = (String) session.getAttribute("NAMEVT");
		String mavaitro = (String) session.getAttribute("KEYVT");
		Integer temp = (Integer) session.getAttribute("SEARCH");
		List<VaiTro> list = null;
		switch (temp) {
		case 1:
			list = this.vaiTroService.findByMavaitro(mavaitro);
			break;
		case 2:
			list = this.vaiTroService.findByTenvaitro(tenvaitro);
			break;
		default:
			break;
		}
		if (list == null) {
			return "redirect:/admin/vaitro/list";
		}
		
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("vaitrolist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPage(pagesize);
		final int goToPage = numberPage - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("vaitrolist", pages);
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
		model.addAttribute("VAITROS", pages);
		return "view-role";
	}
	
	@RequestMapping("/del")
	@PreAuthorize("hasPermission('', 'xoavt')")
	public String delete(ModelMap model, @RequestParam("lvt") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			VaiTro vaiTro = this.vaiTroService.findByVaitroId(long1).get();
			vaiTro.setUpdateday(new Timestamp(new Date().getTime()));
			vaiTro.setNguoiupdate(principal.getName());
			vaiTro.setIsdelete((Integer) 1);
			this.vaiTroService.updateVaitro(vaiTro);
		}
		return "redirect:/admin/vaitro/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Long id) {
		VaiTro nd = this.vaiTroService.findByVaitroId(id).get();
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setIsdelete((Integer) 1);
		vaiTroService.updateVaitro(nd);
		request.getSession().setAttribute("vaitro", null);
		model.addAttribute("VAITROS", vaiTroService.listVaiTro());
		return "redirect:/admin/vaitro/list";
	}
	
	 
}
