package graduate.controller;

import graduate.dto.CategoriesDTO;
import graduate.dto.DishDTO;
import graduate.service.CategoriesService;

import graduate.service.DishService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dish")
public class DishController {

  @Autowired 
  private DishService dishService;

  @Autowired
  private CategoriesService categoriesService;

  @ModelAttribute("categories")//lựa chọn danh mục
  public List<CategoriesDTO> getCategories(){
    return categoriesService.findAll().stream().map(item->{
      CategoriesDTO dto = new CategoriesDTO();
      BeanUtils.copyProperties(item, dto);
      return dto;
    }).collect(Collectors.toList());
  }

  @GetMapping("add")
  public String add(Model model) {
    model.addAttribute("dish", new DishDTO());
    return "restaurantUI/managementDish";
  }

}
