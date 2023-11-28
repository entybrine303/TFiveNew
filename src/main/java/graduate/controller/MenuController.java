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
import graduate.domain.Dish;
import graduate.service.DishService;
@Controller
@RequestMapping("tfive")
public class MenuController {
	@Autowired
	private ManagementCategoryController managementCategoriesController;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DishService dishService;

	void fillAllProduct(ModelMap model) {
		List<Dish> list = dishService.findAll();
		model.addAttribute("products", list);
	}

	void fillRamdom3Product(ModelMap model) {
		List<Dish> list = dishService.findRandomDishes();
		model.addAttribute("rProducts", list);
	}
	
	
	@GetMapping("menu")
	public String viewMenu(ModelMap model) {
		managementCategoriesController.fillToTable(model);
		fillAllProduct(model);
		fillRamdom3Product(model);
		return "customerUI/menu";
	}
	
}
