package graduate.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.Session;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.domain.Categories;
import graduate.dto.CategoriesDTO;
import graduate.service.CategoriesService;





@Controller
@RequestMapping("admin")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;
   @GetMapping("add")
   public String add(Model model) {
	   model.addAttribute("categories", new CategoriesDTO());
	   return "restaurantUI/managementCategories/add";
   }

   
   @PostMapping("saveOrUpdate")
   public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("categories") CategoriesDTO dto, BindingResult result ) {
	   if (result.hasErrors()) {
		
		   return new ModelAndView("restaurantUI/managementCategories/add");
	}
	   
	   Categories entity = new Categories();
	   BeanUtils.copyProperties(dto, entity);
	   categoriesService.save(entity); 
	   model.addAttribute("message", "Category is saved");
	   
	   
	   return new ModelAndView("redirect:/admin/list", model)  ;
   }
   @GetMapping("list")
   public String list(ModelMap model) {
	   List<Categories> list = categoriesService.findAll();
	   model.addAttribute("category", list);
	   return "restaurantUI/managementCategories/list";
   }
   
   @GetMapping("delete/{categoriesId}")
   public ModelAndView delete( ModelMap model, @PathVariable("categoriesId") Long categoriesId) {
	   categoriesService.deleteById(categoriesId);
	   model.addAttribute("message", "Category id delete");
	   return new ModelAndView("redirect:/admin/list", model);
   }

   @GetMapping("edit/{categoriesId}")
   public ModelAndView edit( ModelMap model, @PathVariable("categoriesId") Long categoriesId) {
	   Optional<Categories> opt = categoriesService.findById(categoriesId);
	   CategoriesDTO dto = new CategoriesDTO();
	   
	   if (opt.isPresent()) {
	   Categories entity = opt.get();
		
		BeanUtils.copyProperties(entity, dto);
		dto.setIsEdit(true);
		
		model.addAttribute("categories", dto);
		
		return new ModelAndView("restaurantUI/managementCategories/add", model) ;
	}
	   model.addAttribute("message", "Category is not existed");
	   return new ModelAndView("redirect:/admin/categories/searchpaginated", model) ;
   }
   
   
  
}
