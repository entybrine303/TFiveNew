package graduate.controller;

import java.util.List;

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
public class HomeController {
	@Autowired
	private ManagementCategoryController managementCategoriesController;
	
	@Autowired
	private HttpSession session;

	@Autowired
	private DishService dishService;

	public void fillAllProduct(ModelMap model) {
		List<Dish> list = dishService.findAll();
		model.addAttribute("products", list);
	}
	
	@GetMapping("")
	public String viewHome(ModelMap model) {		
		fillAllProduct(model);
		managementCategoriesController.fillToTable(model);
		return "customerUI/index";
	}
	
}
