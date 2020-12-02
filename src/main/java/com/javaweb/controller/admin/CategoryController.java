package com.javaweb.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.entities.Category;
import com.javaweb.repository.CategoryRepository;
import com.javaweb.service.admin.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryService categorySevice;

	@GetMapping("/")
	@PreAuthorize("hasPermission('', 'themtl')")
	public String add(ModelMap model) {
		Category category = new Category();
		model.addAttribute("CATEGORY", category);
		model.addAttribute("ACTION", "/admin/category/save");
		return "add-category";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("CATEGORY") Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-category";
		} else {
			categorySevice.save(category);
//			return "add-category";
			return "redirect:/admin/category/page/1";
		}
	}

	@PostMapping("/update")
	@PreAuthorize("hasPermission('', 'suatl')")
	public String Update(@Valid @ModelAttribute("CATEGORY") Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-category";
		} else {
		categorySevice.save(category);
//		return "update-category";
		return "redirect:/admin/category/page/1";
	}
	}

	@RequestMapping("/list")
	@PreAuthorize("hasPermission('', 'danhsachtl')")
	public String list(ModelMap model) {
		model.addAttribute("LIST_CATEGORY", categorySevice.findAllCategory());
		return "view-category";
	}

	@RequestMapping("/edit/{id}")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Integer id) {

		Optional<Category> u = categorySevice.findById(id);
		if (u.isPresent()) {
			model.addAttribute("CATEGORY", u.get());
			request.getSession().setAttribute("category", null);
		} else {
			model.addAttribute("CATEGORY", new Category());
		}
		model.addAttribute("ACTION", "/admin/category/update");
		return "update-category";
	}

	@RequestMapping("/delete/{id}")
	@PreAuthorize("hasPermission('', 'xoatl')")
	public String delete(HttpServletRequest request, ModelMap model, @PathVariable(name = "id") Integer id) {
		categorySevice.deleteById(id);
		request.getSession().setAttribute("category", null);
		model.addAttribute("LIST_CATEGORY", categorySevice.findAllCategory());
//		return "view-category";
		return "redirect:/admin/category/page/1";
	}

	@GetMapping("/page/{pageNumber}")
	public String showEmployeePage(Model mode, HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("category");
		int pagesize = 4;
		List<Category> list = (List<Category>) categorySevice.findAllCategory();
		mode.addAttribute("listcategory", categoryRepository.findAllCategory());
		System.out.println(list.size());

		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("category", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/category/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("LIST_CATEGORY", pages);

		return "view-category";
	}

	@GetMapping("/find")
	public String find(ModelMap model, @RequestParam("theloai") String theloai) {
		model.addAttribute("LIST_CATEGORY", categorySevice.findByTheloaiContainingIgnoreCase(theloai));
		return "view-category";
	}

	@GetMapping("/search/{pageNumber}")
	public String search(@RequestParam("theloai") String theloai, Model model, HttpSession session,
			HttpServletRequest request, @PathVariable int pageNumber) {
		if (theloai.equals("")) {
			return "redirect:/admin/category/page/1";
		}
		List<Category> list = categorySevice.findByTheloaiContainingIgnoreCase(theloai);
		if (0 == list.size()) {
			session.setAttribute("messenger", "Không tìm thấy");
		} else {
			PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("categorylist");
			int pagesize = 3;
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);

			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
			request.getSession().setAttribute("categorylist", pages);
			int current = pages.getPage() + 1;
			int begin = Math.max(1, current - list.size());
			int end = Math.min(begin + 5, pages.getPageCount());
			int totalPageCount = pages.getPageCount();
			String baseUrl = "/admin/category/page/";
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", current);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("baseUrl", baseUrl);
			model.addAttribute("LIST_CATEGORY", pages);

		}
		return "view-category";
	}
}
