package graduate.controller.adminController;

import java.util.List;
import java.util.Optional;

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

import graduate.domain.Category;
import graduate.domain.Restaurant;
import graduate.dto.CategoryDTO;
import graduate.service.CategoryService;
import graduate.utils.RamdomID;

@Controller
@RequestMapping("tfive/admin/category/")
public class ManagementCategoryController {

	@Autowired
	private CategoryService categoriesService;

	public void fillToTable(ModelMap model) {
		List<Category> list = categoriesService.findAll();
		model.addAttribute("categories", list);
	}

	@GetMapping("view")
	public String viewForm(ModelMap model) {
		fillToTable(model);

		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.setCategoryID("C-"+RamdomID.generateRandomId());
		model.addAttribute("category", categoryDTO);
		return "restaurantUI/managementCategories";
	}

	@PostMapping("save")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("category") CategoryDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("restaurantUI/managementCategories");
		}

		if (categoriesService.existsById(dto.getCategoryID()) && dto.getIsEdit() == false) {
			model.addAttribute("mess", "ID này đã tồn tại. Vui lòng chọn một ID khác.");
			return new ModelAndView(viewForm(model), model);
		}
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		entity.setRestaurant(new Restaurant("R01"));
		categoriesService.save(entity);
		if (dto.getIsEdit()) {
			model.addAttribute("mess", "Category is update");
		} else {
			model.addAttribute("mess", "Category is saved");
		}

		return new ModelAndView(viewForm(model), model);
	}

	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model, @PathVariable("categoryId") String categoryId) {
		categoriesService.deleteById(categoryId);
		model.addAttribute("mess", "Category id delete");
		return new ModelAndView(viewForm(model), model);
	}

	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") String categoryId) {
		fillToTable(model);
		Optional<Category> opt = categoriesService.findById(categoryId);
		CategoryDTO dto = new CategoryDTO();

		if (opt.isPresent()) {
			Category entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("category", dto);
			return new ModelAndView("restaurantUI/managementCategories", model);
		}
		model.addAttribute("mess", "Category is not existed");

		return new ModelAndView("restaurantUI/managementCategories", model);
	}

}
