package graduate.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.controller.adminController.ManagementCategoryController;
import graduate.domain.Category;
import graduate.domain.Dish;
import graduate.service.CategoryService;
import graduate.service.DishService;
@Controller
@RequestMapping("tfive")
public class ProductDetailController {
	@Autowired
	private ManagementCategoryController managementCategoriesController;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private DishService dishService;

	public void fillAllProduct(ModelMap model) {
		List<Dish> list = dishService.findAll();
		model.addAttribute("products", list);
	}

	public void fillProduct(ModelMap model, String productID) {
		try {
			Optional<Dish> product = dishService.findById(productID);
			Dish getProduct = product.get();
			model.addAttribute("product", getProduct);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}
	
	@GetMapping("product/{dishID}")
	public String viewProductDetail(ModelMap model, @PathVariable("dishID") String productID) {
		
		fillProduct(model, productID);
		fillAllProduct(model);
		return "customerUI/product-detail";
	}
	
}
