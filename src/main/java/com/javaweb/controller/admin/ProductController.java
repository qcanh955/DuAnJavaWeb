package com.javaweb.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.mock.web.MockMultipartFile;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.common.Handle;
import com.javaweb.controller.client.BaseController;
import com.javaweb.dto.ProductDto;
import com.javaweb.entities.Category;
import com.javaweb.entities.Product;
import com.javaweb.repository.ProductRepository;
import com.javaweb.service.admin.CategoryService;
import com.javaweb.service.admin.ProductService;
import com.javaweb.service.client.CProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductController extends BaseController{

	/*
	 * @Autowired ProductServiceImpl productService;
	 * 
	 * @Autowired private CartManage cartManager;
	 * 
	 * @Autowired CategoryService categoryService;
	 * 
	 * @GetMapping("/product") public String index(Model model, HttpServletRequest
	 * request, RedirectAttributes redirect) {
	 * request.getSession().setAttribute("productlist", null);
	 * 
	 * if (model.asMap().get("success") != null)
	 * redirect.addFlashAttribute("success",
	 * model.asMap().get("success").toString()); return
	 * "redirect:/admin/product/page/1"; }
	 * 
	 * @GetMapping("/product/page/{pageNumber}") public String
	 * showcategoryPage(HttpServletRequest request, @PathVariable int pageNumber,
	 * Model model) { PagedListHolder<?> pages = (PagedListHolder<?>)
	 * request.getSession().getAttribute("productlist"); int pagesize = 2;
	 * List<Product> list = (List<Product>) productService.listAll();
	 * System.out.println(list.size()); if (pages == null) { pages = new
	 * PagedListHolder<>(list); pages.setPageSize(pagesize); } else { final int
	 * goToPage = pageNumber - 1; if (goToPage <= pages.getPageCount() && goToPage
	 * >= 0) { pages.setPage(goToPage); } }
	 * request.getSession().setAttribute("productlist", pages); int current =
	 * pages.getPage() + 1; int totalItems = pages.getNrOfElements(); int begin =
	 * Math.max(1, current - list.size()); int end = Math.min(begin + 5,
	 * pages.getPageCount());
	 * 
	 * int totalPageCount = pages.getPageCount(); String baseUrl =
	 * "/admin/product/page/";
	 * 
	 * model.addAttribute("beginIndex", begin); model.addAttribute("endIndex", end);
	 * model.addAttribute("totalItems", totalItems);
	 * model.addAttribute("currentIndex", current);
	 * model.addAttribute("totalPageCount", totalPageCount);
	 * model.addAttribute("baseUrl", baseUrl); model.addAttribute("products",
	 * pages);
	 * 
	 * return "view-product"; }
	 * 
	 * @GetMapping("/product/create") public String create(Model model) {
	 * model.addAttribute("PRODUCTDTO", new ProductDto());
	 * //model.addAttribute("ACTION", "/admin/product/save"); return "add-product";
	 * }
	 * 
	 * @PostMapping("/product/create") public String
	 * processCreate(@Valid @ModelAttribute("PRODUCTDTO") ProductDto dto,
	 * BindingResult bindingResult) { if (bindingResult.hasErrors()) { return
	 * "add-product"; }
	 * 
	 * return "redirect:/admin/product"; }
	 * 
	 * @GetMapping("/product/{id}/edit") public String edit(ModelMap model,
	 * HttpServletRequest request, @PathVariable(name = "id") Long id) {
	 * Optional<Product> opProduct = productService.findById(id); ProductDto dto =
	 * null; if (opProduct.isPresent()) { Product pt = opProduct.get(); File file =
	 * new File("uploads/" + pt.getImage()); FileInputStream input; try { input =
	 * new FileInputStream(file); MultipartFile mutiPhoto = new
	 * MockMultipartFile("file", file.getName(), "text/plain",
	 * IOUtils.toByteArray(input));
	 * 
	 * dto = new ProductDto(pt.getId(), pt.getName(),pt.getPlsy(), pt.getNxb(),
	 * pt.getAuthor(), pt.getPagenum(), mutiPhoto, pt.getPrice(),
	 * pt.getCategorys().getId(), pt.getSale(), pt.isHighlight(),
	 * pt.isNew_product(), pt.getDetails(), pt.getCreated_at(), pt.getUpdated_at());
	 * 
	 * } catch (FileNotFoundException e) { // TODO: handle exception
	 * e.printStackTrace(); } catch (Exception e) { // TODO: handle exception
	 * e.printStackTrace(); } model.addAttribute("PRODUCTDTO", dto);
	 * request.getSession().setAttribute("productlist", null); } else {
	 * model.addAttribute("PRODUCTDTO", new ProductDto()); }
	 * model.addAttribute("ACTION", "/admin/product/save"); return "add-product"; }
	 * 
	 * @PostMapping("/product/save") public String save(HttpServletRequest
	 * request,ModelMap model, @ModelAttribute("PRODUCTDTO") ProductDto
	 * dto,RedirectAttributes redirect) {
	 * 
	 * Optional<Product> optionalProduct = productService.findById(dto.getId());
	 * Product products = null; String image = "Logo.png"; Path path =
	 * Paths.get("uploads/"); if (optionalProduct.isPresent()) { // save
	 * 
	 * 
	 * if (dto.getImage().isEmpty()) { image = optionalProduct.get().getImage();
	 * 
	 * } else { try { InputStream inputStream = dto.getImage().getInputStream();
	 * Files.copy(inputStream, path.resolve(dto.getImage().getOriginalFilename()),
	 * StandardCopyOption.REPLACE_EXISTING); image =
	 * dto.getImage().getOriginalFilename().toString(); } catch (Exception e) { //
	 * TODO: handle exception e.printStackTrace(); } } } else { // add new if
	 * (!dto.getImage().isEmpty()) { try { InputStream inputStream =
	 * dto.getImage().getInputStream(); Files.copy(inputStream,
	 * path.resolve(dto.getImage().getOriginalFilename()),
	 * StandardCopyOption.REPLACE_EXISTING); image =
	 * dto.getImage().getOriginalFilename().toString(); } catch (Exception e) { //
	 * TODO: handle exception e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * products = new Product(dto.getId(), dto.getName(), dto.getPlsy(),
	 * dto.getNxb(), dto.getAuthor(), dto.getPagenum(), dto.getPrice(), image, new
	 * Category(dto.getId_category(), image), dto.getSale(), dto.isHighlight(),
	 * dto.isNew_product(), dto.getDetails(), dto.getCreated_at(),
	 * dto.getUpdated_at()); productService.save(products);
	 * request.getSession().setAttribute("productlist", null);
	 * redirect.addFlashAttribute("success", "Saved product successfully!"); return
	 * "redirect:/admin/product"; }
	 * 
	 * @GetMapping("/product/{id}/delete") public String delete(ModelMap
	 * model, @PathVariable(name = "id") Integer id) { productService.delete(id);;;
	 * model.addAttribute("productlist", productService.listAll()); return
	 * "redirect:/admin/product"; }
	 * 
	 * @GetMapping("/product/search/{pageNumber}") public String
	 * search(@RequestParam("s") String s, Model model, HttpServletRequest request,
	 * 
	 * @PathVariable int pageNumber) { if (s.equals("")) { return
	 * "redirect:/admin/product"; } List<Product> list =
	 * productService.findBynameContainingIgnoreCase(s); if (list == null) { return
	 * "redirect:/admin/product"; } PagedListHolder<?> pages = (PagedListHolder<?>)
	 * request.getSession().getAttribute("productlist"); int pagesize = 2; pages =
	 * new PagedListHolder<>(list); pages.setPageSize(pagesize);
	 * 
	 * final int goToPage = pageNumber - 1; if (goToPage <= pages.getPageCount() &&
	 * goToPage >= 0) { pages.setPage(goToPage); }
	 * request.getSession().setAttribute("productlist", pages); int current =
	 * pages.getPage() + 1; int begin = Math.max(1, current - list.size()); int end
	 * = Math.min(begin + 5, pages.getPageCount()); int totalPageCount =
	 * pages.getPageCount(); String baseUrl = "/admin/product/page/";
	 * model.addAttribute("beginIndex", begin); model.addAttribute("endIndex", end);
	 * model.addAttribute("currentIndex", current);
	 * model.addAttribute("totalPageCount", totalPageCount);
	 * model.addAttribute("baseUrl", baseUrl); model.addAttribute("products",
	 * pages); return "view-product"; }
	 * 
	 * @ModelAttribute(name = "CATEGORY") public List<Category> getAllCategory(){
	 * return productService.findAllCategory(); }
	 */
	@Autowired
	ProductService service;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	private CProductService _productService;

//	@PostMapping("/changePrice")
//	public String changePrice(@RequestParam("id") Long id, @RequestParam("newPrice") Double price,
//			HttpServletRequest request) {
//		service.changeProductPrice(id, price);
//		request.getSession().setAttribute("cartlist", null);
//		return "redirect:/admin/product/page/1";
//	}

	@RequestMapping("/chi-tiet-san-pham/{id}")
	public ModelAndView products(@PathVariable int id) {
		_mvShare.setViewName("product_details");
		_mvShare.addObject("product", _productService.GetProductByID(id));
		int idCategory = _productService.GetProductByID(id).getId_category();
		_mvShare.addObject("productByIDCategory", _productService.GetProductByIDCategory(idCategory));
		return _mvShare;
	}


	@GetMapping("/page/{pageNumber}")
	@PreAuthorize("hasPermission('', 'danhsachprd')")
	public String showEmployeePage(Model mode, HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cartlist");
		int pagesize = 5;
		List<Product> list = (List<Product>) service.findAll();
		mode.addAttribute("listproducts", productRepository.findAll());
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
		request.getSession().setAttribute("cartlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/product/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("products", pages);

		return "view-product";
	}

	@GetMapping("/")
//	@PreAuthorize("hasPermission('', 'themprd')")
	public String addOrEdit(ModelMap model) {
		ProductDto productDTO = new ProductDto();
		model.addAttribute("PRODUCTDTO", productDTO);
		model.addAttribute("ACTION", "/admin/product/saveOrUpdate");
		return "add-product";
	}

	@RequestMapping("/edit/{id}")
	@PreAuthorize("hasPermission('', 'suaprd')")
	public String edit(ModelMap model, HttpServletRequest request, @PathVariable(name = "id") Long id) {
		Optional<Product> opProduct = service.findById(id);
		ProductDto dto = null;
		if (opProduct.isPresent()) {
			Product pt = opProduct.get();
			File file = new File("uploads/" + pt.getImage());
			FileInputStream input;
			try {
				input = new FileInputStream(file);
				MultipartFile mutiPhoto = new MockMultipartFile("file", file.getName(), "text/plain",
						IOUtils.toByteArray(input));

				dto = new ProductDto(pt.getId(), pt.getName(), pt.getPlsy(), pt.getNxb(), pt.getAuthor(), pt.getPagenum(), mutiPhoto, pt.getPrice(), pt.getCategorys().getId(),
						pt.getSale(), pt.isHighlight(), pt.isNew_product(), pt.getDetails(), pt.getCreated_at(),
						pt.getUpdated_at());

			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			model.addAttribute("PRODUCTDTO", dto);
			request.getSession().setAttribute("cartlist", null);
		} else {
			model.addAttribute("PRODUCTDTO", new ProductDto());
		}
		model.addAttribute("ACTION", "/admin/product/saveOrUpdate");
		return "update-product";
	}

	@PostMapping("/saveOrUpdate")
//	@PreAuthorize("hasPermission('', 'themprd')")
	public String save(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute("PRODUCTDTO") ProductDto dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-product";
		}
		Optional<Product> optionalProduct = service.findById(dto.getId());
		Product products = null;
		String image = "Logo.png";
		Path path = Paths.get("uploads/");
		if (optionalProduct.isPresent()) {
			// save

			if (dto.getImage().isEmpty()) {
				image = optionalProduct.get().getImage();

			} else {
				try {
					InputStream inputStream = dto.getImage().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getImage().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = dto.getImage().getOriginalFilename().toString();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} else {
			// add new
			if (!dto.getImage().isEmpty()) {
				try {
					InputStream inputStream = dto.getImage().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getImage().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = dto.getImage().getOriginalFilename().toString();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		}

		products = new Product(dto.getId(), dto.getName(), dto.getPlsy(), dto.getNxb(), dto.getAuthor(), dto.getPagenum(),dto.getPrice(), image,
				new Category(dto.getId_category(), image), dto.getSale(), dto.isHighlight(), dto.isNew_product(),
				dto.getDetails(), dto.getCreated_at(), dto.getUpdated_at());
		service.save(products);
		request.getSession().setAttribute("cartlist", null);
//		return "add-product";
		return "redirect:/admin/product/page/1";
	}

	@GetMapping("/list")
	public String list(ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("cartlist", null);
		return "redirect:/admin/product/page/1";
	}

	@RequestMapping("/dataSearch")
	public String dataSearch(@RequestParam("nameprd") String nameprd,
			@RequestParam(name = "categorySearch[]", required = false) List<Long> categoryid, HttpSession session) {
		session.setAttribute("NAMEPRD", nameprd);
		session.setAttribute("CATE", categoryid);
		if (null == nameprd || nameprd.equals("")) {
			if (null == categoryid) {
				return "redirect:/admin/product/list";
			} else {
				session.setAttribute("SEARCH", 3);
				return "redirect:/admin/product/search/1";
			}
		} else {
			if (categoryid != null) {
				nameprd = Handle.xuLySearch(nameprd);
				session.setAttribute("KEYWORD", nameprd);
				session.setAttribute("SEARCH", 1);
				return "redirect:/admin/product/search/1";
			} else {
				nameprd = Handle.xuLySearch(nameprd);
				session.setAttribute("KEYWORD", nameprd);
				session.setAttribute("SEARCH", 4);
				return "redirect:/admin/product/search/1";
			
				
			}
		}
	}

	@GetMapping("/search/{pageNumber}")
	public String search( Model model, HttpSession session, HttpServletRequest request, @PathVariable int pageNumber) {
		String nameprd = (String) session.getAttribute("KEYWORD");
		@SuppressWarnings("unchecked")
		List<Long> categoryid = (List<Long>) session.getAttribute("CATE");
		Integer temp = (Integer) session.getAttribute("SEARCH");	
		List<Product> list = null;
		switch (temp) {
		case 1:
			list = this.service.findAllByNameAndTL(nameprd ,categoryid);
			break;
		case 2:
			list = this.service.findAllByName(nameprd);
			break;
		case 3:
			list = this.service.findAllByTL(categoryid);
			break;
		case 4:
			list = this.service.findByNameContainingIgnoreCase(nameprd);
			break;
		default:
			break;
		}
		
		if (list == null) {
			return "redirect:/admin/product/page/1";
		}
			PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cartlist");
			int pagesize = 3;
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);

			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
			request.getSession().setAttribute("cartlist", pages);
			int current = pages.getPage() + 1;
			int begin = Math.max(1, current - list.size());
			int end = Math.min(begin + 5, pages.getPageCount());
			int totalPageCount = pages.getPageCount();
			String baseUrl = "/admin/product/page/";
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", current);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("baseUrl", baseUrl);
			model.addAttribute("products", pages);
			return"view-product";
		}


	@ModelAttribute(name = "CATEGORY")
	public List<Category> getAllCategory() {
		return service.findAllCategory();
	}

	@RequestMapping("/delete/{id}")
	@PreAuthorize("hasPermission('', 'xoaprd')")
	public String deleteProduct(HttpServletRequest request, @PathVariable(name = "id") long id) {
		service.deleteById(id);
		request.getSession().setAttribute("cartlist", null);
		return "redirect:/admin/product/page/1";
	}
	

}
