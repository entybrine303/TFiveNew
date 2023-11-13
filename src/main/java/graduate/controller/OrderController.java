package graduate.controller;

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

import graduate.domain.Customer;
import graduate.service.CustomerService;

@Controller
@RequestMapping("tfive")
public class OrderController {

	@Autowired
	private HttpSession session;

	@Autowired
	private CustomerService customerService;
	
	public void fillCustomerInfo(ModelMap model) {
		try {
			Customer customer = customerService.findByUsername(session.getAttribute("username").toString());
			model.addAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}
	
	@GetMapping("checkout")
	public String viewCheckout(ModelMap model) {
		fillCustomerInfo(model);
		
		return "customerUI/checkout";
	}
	
	@GetMapping("order-detail")
	public String viewOrderDetail(ModelMap model) {
		
		return "customerUI/order-detail";
	}
	
}
