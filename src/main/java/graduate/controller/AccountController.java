package graduate.controller;

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
@Controller
@RequestMapping("tfive")
public class AccountController {
	
	@GetMapping("my-order")
	public String viewOrder(ModelMap model) {
		
		return "customerUI/my-order";
	}
	
	@GetMapping("profile")
	public String viewProfile(ModelMap model) {
		
		return "customerUI/profile";
	}
	
}
