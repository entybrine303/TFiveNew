package graduate.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import graduate.domain.Account;
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import graduate.domain.Restaurant;
import graduate.dto.CustomerDTO;
import graduate.dto.DishDTO;
import graduate.service.CustomerService;
import graduate.service.StorageService;
@Controller
@RequestMapping("tfive")
public class AccountController {

	@Autowired
	private HttpSession session;

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("my-order")
	public String viewOrder(ModelMap model) {
		return "customerUI/my-order";
	}
	
	public void fillCustomerInfo(ModelMap model) {
		try {
			Customer customer = customerService.findByUsername(session.getAttribute("username").toString());
			if (customer.getSex()==null) {
				customer.setSex(true);
			}
			model.addAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}
	
	@GetMapping("profile")
	public String viewProfile(ModelMap model) {
		
		fillCustomerInfo(model);
		
		return "customerUI/profile";
	}
	

	@PostMapping("profile/saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("customer") CustomerDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(viewProfile(model));
		}

		Customer entity =new Customer();
		BeanUtils.copyProperties(dto, entity);
		
		if (!dto.getImageFile().isEmpty()) {
			entity.setImg(storageService.getStoredFileName(dto.getImageFile(), dto.getCustomerID()));
			storageService.store(dto.getImageFile(), entity.getImg());
		}
		
		Account account=new Account(session.getAttribute("username").toString());
		entity.setAccount(account);
		
		customerService.save(entity);
		model.addAttribute("mess", "Product is saved");
		
		return new ModelAndView(viewProfile(model), model);
	}

}
