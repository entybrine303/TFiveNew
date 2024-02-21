package graduate.controller;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import graduate.dto.CartDTO;
import graduate.dto.DishDTO;
import graduate.service.CategoryService;
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

	@Autowired
	private CategoryService categoryService;

	private List<Dish> list;
	private List<Category> cateList;

	void fillAllProduct(ModelMap model) {
		this.list = dishService.findAll();
		model.addAttribute("fillAll", true);
		model.addAttribute("count", list.size());
		model.addAttribute("products", list);
	}

	void fillProductsByCategory(ModelMap model, String categoryID) {
		this.list = dishService.findByCategoryId(categoryID);

		model.addAttribute("count", list.size());
		model.addAttribute("products", list);
	}

	void filterByPrice(ModelMap model, Double min, Double max) {
		 List<Dish> filteredDishes = this.list.stream()
	                .filter(dish -> dish.getDiscountPrice() != null && dish.getDiscountPrice() >= min && dish.getDiscountPrice() <= max)
	                .collect(Collectors.toList());
		model.addAttribute("count", filteredDishes.size());
		model.addAttribute("products", filteredDishes);
	}

	void sortByLastCreatedDate(ModelMap model) {
		model.addAttribute("count", this.list.size());
		this.list.sort((dish1, dish2) -> dish2.getCreatedDate().compareTo(dish1.getCreatedDate()));
		model.addAttribute("products", this.list);
	}

	void sortByPriceDecrease(ModelMap model, boolean choose) {
		model.addAttribute("count", this.list.size());

		List<Dish> sortedDishes;
		if (choose == true) {
			sortedDishes = this.list.stream().sorted((dish1, dish2) -> dish2.getDiscountPrice().compareTo(dish1.getDiscountPrice()))
					.collect(Collectors.toList());
		} else {
			sortedDishes = this.list.stream().sorted(Comparator.comparing(Dish::getDiscountPrice)).collect(Collectors.toList());
		}
		model.addAttribute("products", sortedDishes);
	}

//	Đây là phương thức fill sp và cate mặc định
	public void fillDefaut(ModelMap model) {
		 cateList= categoryService.findAll();
		model.addAttribute("categories", cateList);
	}

	@GetMapping("menu")
	public String viewMenu(ModelMap model) {
		fillDefaut(model);
		fillAllProduct(model);

		model.addAttribute("option", 0);
		return "customerUI/menu";
	}

	@GetMapping("menu/category/{categoryID}")
	public String viewDishByCategory(ModelMap model, @PathVariable("categoryID") String categoryID) {
		fillDefaut(model);
		fillProductsByCategory(model, categoryID);
		return "customerUI/menu";
	}

	@GetMapping("menu/sortBy/createdDate")
	public String viewDishAreSortByCreatedDate(ModelMap model) {
		fillDefaut(model);
		sortByLastCreatedDate(model);
		model.addAttribute("option", 1);
		return "customerUI/menu";
	}

	@GetMapping("menu/sortBy/priceDecrease")
	public String viewDishAreSortByPriceDecrease(ModelMap model) {
		fillDefaut(model);
		sortByPriceDecrease(model, true);
		model.addAttribute("option", 3);
		return "customerUI/menu";
	}

	@GetMapping("menu/sortBy/priceIncrease")
	public String viewDishAreSortByPriceIncrease(ModelMap model) {
		fillDefaut(model);
		sortByPriceDecrease(model, false);
		model.addAttribute("option", 4);
		return "customerUI/menu";
	}

	@PostMapping("menu/filterByPrice")
	public String viewFilterDishByPrice(ModelMap model, @Valid @ModelAttribute("dish") DishDTO dto,
			BindingResult result) {
		fillDefaut(model);
		filterByPrice(model, dto.getMin(), dto.getMax());
		return "customerUI/menu";
	}

}
