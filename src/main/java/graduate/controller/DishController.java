package graduate.controller;

import graduate.dto.CategoriesDTO;
import graduate.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dish")
public class DishController {
  @Autowired
  private CategoriesService categoriesService;

  @GetMapping("add")
  public String add(Model model) {
    model.addAttribute("categories", new CategoriesDTO());
    return "restaurantUI/managementDish";
  }
}
